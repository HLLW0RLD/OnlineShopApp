package com.example.home.utils

import com.example.api.data.LatestDTO
import com.example.api.data.ProductDTO

sealed class LatestRvState {
    data class Success(val productData: LatestDTO): LatestRvState()
    object Loading: LatestRvState()
}
