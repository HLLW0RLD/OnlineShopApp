package com.example.api.repository

import com.example.api.data.FlashSaleDTO
import com.example.api.data.LatestDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun getLatest(): Observable<LatestDTO>
    fun getFlashSale(): Observable<FlashSaleDTO>
}