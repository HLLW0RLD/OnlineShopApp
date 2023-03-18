package com.example.home.utils

import com.example.api.data.FlashSaleDTO

sealed class FlashSalesRvState {
    data class Success(val productData: FlashSaleDTO): FlashSalesRvState()
    object Loading: FlashSalesRvState()
}