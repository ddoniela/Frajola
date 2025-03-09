package com.example.frajola

import androidx.annotation.DrawableRes

data class Product(
    val product: String,
    val description: String,
    val price: Double,
    @DrawableRes val icon: Int
)
