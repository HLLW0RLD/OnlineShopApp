package com.example.api.repository

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import com.example.api.remote.ProductApi
import io.reactivex.rxjava3.core.Single

class RemoteRepository(val api: ProductApi): IRemoteRepository {

    override fun getLatest(): Single<Latest> {
        return  api.getLatest()
    }

    override fun getFlashSale(): Single<FlashSale> {
        return api.getFlashSale()
    }
}