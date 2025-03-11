package com.example.frajola

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var adapter: ProductListAdapter

    private val categories = listOf("Tudo", "Traje completo", "Terno", "Colete", "Gravata")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvList = findViewById<RecyclerView>(R.id.rv_products_list)
        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories_list)
        adapter = ProductListAdapter(products)

        categoryAdapter = CategoryAdapter(categories) { category ->
            filterProducts(category)
        }

        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoryAdapter


        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        adapter.submitList(products)
        filterProducts("Tudo")
        adapter.setOnClickListener { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product.product)
            intent.putExtra("description", product.description)
            intent.putExtra("price", product.price)
            intent.putExtra("image", product.icon)
            startActivity(intent)
        }
    }
    private fun filterProducts(category: String) {
        val filteredProducts = if (category == "Tudo") {
            products
        } else {
            products.filter {  it.category.equals(category, ignoreCase = true) }
        }
        adapter.submitList(filteredProducts)
    }
}

val products = listOf(
    Product(
        "Traje Completo Dourado",
        "Traje completo dourado dos anos 90 com detalhes floridos.",
        "Traje completo",
        788.99,
        R.drawable.traje_completo1
    ),
    Product(
        "Traje completo Roxo",
        "Traje completo na cor púrpura. Elegante e especial.",
        "Traje completo",
        1020.99,
        R.drawable.traje_completo_roxo
    ),
    Product(
        "Colete cinza",
        "Colete essencial para um look refinado e clássico.",
        "Colete",
        150.00,
        R.drawable.colete_cinza
    ),
    Product(
        "Gravata listrada",
        "Ideal para ternos sóbrios. Toque criativo sem deixar de ser clássico",
        "Gravata",
        40.50,
        R.drawable.gravata_masculina_azul_listra
    ),
    Product(
        "Colete vermelho",
        "Aquele toque de cor para não passar despercebido.",
        "Colete",
        200.99,
        R.drawable.colete_vermelho
    ),
    Product(
        "Terno Dourado",
        "O terno elegante que vai te trazer notoriedade.",
        "Terno",
        200.99,
        R.drawable.terno_masculino_dourado_paisley_huge_1
    )
)