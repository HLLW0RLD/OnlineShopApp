package com.example.features.utils

import com.example.app.base.Product

sealed class LatestRvState {
    data class Success(val productData: List<Product>): LatestRvState()
    object Loading: LatestRvState()
}
