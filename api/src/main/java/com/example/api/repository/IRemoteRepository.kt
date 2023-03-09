package com.example.api.repository

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun getLatest(): Single<LatestDTO>
    fun getFlashSale(): Single<FlashSaleDTO>
}