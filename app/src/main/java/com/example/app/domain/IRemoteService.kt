package com.example.app.domain

import com.example.app.base.Product
import io.reactivex.rxjava3.core.Observable

interface IRemoteService {

    fun getLatest(): Observable<List<Product>>

    fun getFlashSale(): Observable<List<Product>>
}