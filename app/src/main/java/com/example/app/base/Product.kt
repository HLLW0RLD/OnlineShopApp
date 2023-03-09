package com.example.app.base

/* Class for simplified data transfer between view elements */
data class Product(val category: String,
                   val name: String,
                   val price: Float,
                   val discount: Int,
                   val image_url: String)
