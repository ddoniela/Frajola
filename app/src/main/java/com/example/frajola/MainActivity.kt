package com.example.frajola

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frajola.ui.theme.FrajolaTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvList = findViewById<RecyclerView>(R.id.rv_list)
        val adapter = ProductListAdapter()

        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        adapter.submitList(products)
        adapter.setOnClickListener { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product.product)
            intent.putExtra("description", product.description)
            intent.putExtra("price", product.price)
            intent.putExtra("image", product.icon)
            startActivity(intent)
        }
    }
}

val products = listOf(
    Product(
        "Traje Completo Dourado",
        "Traje completo dourado dos anos 90 com detalhes floridos.",
        788.99,
        R.drawable.traje_completo1
    ),
    Product(
        "Terno Roxo",
        "Traje completo roxo.",
        1020.99,
        R.drawable.traje_completo_roxo
    ),
    Product(
        "Colete cinza",
        "Colete essencial para um look refinado e clássico.",
        150.00,
        R.drawable.colete_cinza
    ),
    Product(
        "Gravata listrada",
        "Ideal para ternos sóbrios. Toque criativo sem deixar de ser clássico",
        40.50,
        R.drawable.gravata_masculina_azul_listra
    ),
    Product(
        "Colete vermelho",
        "Aquele toque de cor para não passar despercebido.",
        200.99,
        R.drawable.colete_vermelho
    ),
    Product(
        "Terno Dourado",
        "O terno elegante que vai te trazer notoriedade.",
        200.99,
        R.drawable.terno_masculino_dourado_paisley_huge_1
    )
)