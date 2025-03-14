package com.example.frajola

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var adapter: ProductListAdapter

    private val categories = listOf("Tudo", "Terno", "Smoking", "Camisa", "Gravata")


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
        "Terno bege",
        "Ajustado para a perfeição.",
        "Terno",
        788.99,
        R.drawable.terno_bege
    ),
    Product(
        "Gravata borboleta",
        "Feita de seda monocromática.",
        "Gravata",
        1020.99,
        R.drawable.gravata_borboleta
    ),
    Product(
        "Paletó bege de luxo",
        "Essencial para um look refinado e clássico.",
        "Terno",
        150.00,
        R.drawable.paleto_bege
    ),
    Product(
        "Smoking de lã italiana",
        "Elegância extrema.",
        "Smoking",
        150.00,
        R.drawable.terno_branco
    ),
    Product(
        "Camisa branca de linho",
        "Atemporal e chique.",
        "Camisa",
        200.99,
        R.drawable.camisa_branca
    )
)