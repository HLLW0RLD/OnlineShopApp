package com.example.api.remote

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import io.reactivex.rxjava3.core.Single

private const val URL = "https://run.mocky.io/v3/"
class ProductApi(private val webClient: ProductInterface) {

    fun getLatest(): Single<Latest>{
        return webClient.getLatest()
    }

    fun getFlashSale(): Single<FlashSale>{
       return webClient.getFlashSale()
    }
}