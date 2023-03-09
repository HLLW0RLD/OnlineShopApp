package com.example.features.utils

import com.example.app.base.Product

sealed class FlashSalesRvState {
    data class Success(val productData: List<Product>): FlashSalesRvState()
    object Loading: FlashSalesRvState()
}