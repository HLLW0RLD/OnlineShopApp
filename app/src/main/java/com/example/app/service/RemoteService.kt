package com.example.app.service

import com.example.api.data.FlashSale
import com.example.api.data.Latest
import com.example.api.repository.IRemoteRepository
import com.example.app.domain.IRemoteService
import io.reactivex.rxjava3.core.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteService(private val remote: IRemoteRepository): IRemoteService {

    override fun getLatest(): Single<Latest> {
        return  remote.getLatest()
    }

    override fun getFlashSale(): Single<FlashSale> {
        return remote.getFlashSale()
    }
}