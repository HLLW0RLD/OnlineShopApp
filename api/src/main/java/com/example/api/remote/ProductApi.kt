package com.example.api.remote

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import io.reactivex.rxjava3.core.Single

private const val URL = "https://run.mocky.io/v3/"
class ProductApi(private val webClient: ProductInterface) {

    fun getLatest(): Single<LatestDTO>{
        return webClient.getLatest()
    }

    fun getFlashSale(): Single<FlashSaleDTO>{
       return webClient.getFlashSale()
    }
}