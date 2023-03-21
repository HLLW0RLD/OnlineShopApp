package com.example.api.data

data class ProductDTO(
    val category: String,
    val name: String,
    val price: Float,
    val discount: Int,
    val image_url: String
)