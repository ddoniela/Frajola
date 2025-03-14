package com.example.frajola

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val tvProductDetail = findViewById<TextView>(R.id.tv_product_detail)
        val tvDescriptionDetail = findViewById<TextView>(R.id.tv_description_detail)
        val tvPriceDetail = findViewById<TextView>(R.id.tv_price_detail)
        val tvImageDetail = findViewById<ImageView>(R.id.image_detail)
        val btnBack = findViewById<ImageView>(R.id.btn_back)

        val product = intent.getStringExtra("product")
        val description = intent.getStringExtra("description")
        val price = intent.getDoubleExtra("price", 0.0)
        val image = intent.getIntExtra("image", R.drawable.colete_vermelho)

        tvProductDetail.text = product
        tvDescriptionDetail.text = description
        tvPriceDetail.text = "R$ " + String.format(Locale.ROOT, "%.2f", price)
        tvImageDetail.setImageResource(image)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}