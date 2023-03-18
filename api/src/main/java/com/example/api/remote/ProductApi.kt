package com.example.api.remote

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

private const val URL = "https://run.mocky.io/v3/"
class ProductApi(private val webClient: ProductInterface) {

    fun getLatest(): Observable<LatestDTO> {
        return webClient.getLatest()
    }

    fun getFlashSale(): Observable<FlashSaleDTO>{
       return webClient.getFlashSale()
    }
}