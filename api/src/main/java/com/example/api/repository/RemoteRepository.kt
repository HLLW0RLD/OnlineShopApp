package com.example.api.repository

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import com.example.api.remote.ProductApi
import io.reactivex.rxjava3.core.Single

class RemoteRepository(val api: ProductApi): IRemoteRepository {

    override fun getLatest(): Single<LatestDTO> {
        return  api.getLatest()
    }

    override fun getFlashSale(): Single<FlashSaleDTO> {
        return api.getFlashSale()
    }
}