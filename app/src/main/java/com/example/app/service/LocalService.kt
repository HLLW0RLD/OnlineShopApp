package com.example.app.service

import android.util.Log
import com.example.app.domain.ILocalService
import com.example.app.utils.Constants.LOCAL_SERVICE_TAG
import com.example.core.db.UserEntity
import com.example.core.repository.ILocalRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent

class LocalService(private val local: ILocalRepository) : ILocalService {

    override fun insert(firstName: String, lastName: String, email: String) {
        Single
            .just(String)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    local.insert(
                        UserEntity(
                            0,
                            firstName,
                            lastName,
                            email
                        )
                    )
                }
            )
    }

    override fun getUserByName(name: String): Boolean {
        local.getUserByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.d(LOCAL_SERVICE_TAG, "saved name = ${it.firstName}")
                return@map it.firstName != name
            }
            .subscribe()
        return false
    }

    override fun delete(firstName: String, lastName: String, email: String) {
        local.delete(
            UserEntity(
                0,
                firstName,
                lastName,
                email
            )
        )
    }
}