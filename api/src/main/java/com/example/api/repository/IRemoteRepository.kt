package com.example.api.repository

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun getLatest(): Single<Latest>
    fun getFlashSale(): Single<FlashSale>
}