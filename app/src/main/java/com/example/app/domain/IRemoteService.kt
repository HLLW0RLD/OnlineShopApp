package com.example.app.domain

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import io.reactivex.rxjava3.core.Single

interface IRemoteService {
    fun getLatest(): Single<Latest>

    fun getFlashSale(): Single<FlashSale>
}