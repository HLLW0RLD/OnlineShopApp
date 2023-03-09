package com.example.app.service

import com.example.api.repository.IRemoteRepository
import com.example.app.base.Product
import com.example.app.domain.IRemoteService
import com.example.app.utils.Helper
import io.reactivex.rxjava3.core.Observable

class RemoteService(private val remote: IRemoteRepository) : IRemoteService {

    override fun getLatest(): Observable<List<Product>> {
        return remote
            .getLatest()
            .toObservable()
            .switchMap { dto ->
                Observable.fromIterable(dto.latest)
                    .flatMap {
                        Helper.convertDtoToData(it).toObservable()
                    }
                    .toList()
                    .toObservable()
            }
            .map { list -> list.flatten() }
    }

    override fun getFlashSale(): Observable<List<Product>> {
        return remote
            .getFlashSale()
            .toObservable()
            .switchMap { dto ->
                Observable.fromIterable(dto.flash_sale)
                    .flatMap {
                        Helper.convertDtoToData(it).toObservable()
                    }
                    .toList()
                    .toObservable()
            }
            .map { list -> list.flatten() }
    }
}