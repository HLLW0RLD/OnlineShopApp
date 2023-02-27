package com.example.api.remote

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://run.mocky.io/v3/"
class ProductApi() {
    private val webClient: ProductInterface = Retrofit.Builder()
        .baseUrl(URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(ProductInterface::class.java)

    fun getLatest(): Single<Latest>{
        return webClient.getLatest()
    }

    fun getFlashSale(): Single<FlashSale>{
       return webClient.getFlashSale()
    }
}