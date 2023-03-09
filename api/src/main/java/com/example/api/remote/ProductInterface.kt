package com.example.api.remote

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ProductInterface {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    fun getLatest(): Single<LatestDTO>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    fun getFlashSale(): Single<FlashSaleDTO>
}