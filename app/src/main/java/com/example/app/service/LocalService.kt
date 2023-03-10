package com.example.app.service

import android.util.Log
import com.example.app.domain.ILocalService
import com.example.app.utils.Constants
import com.example.app.utils.Helper
import com.example.core.db.UserEntity
import com.example.core.repository.ILocalRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalService(private val local: ILocalRepository) : ILocalService {

    override fun insert(firstName: String, lastName: String, email: String) {
        local.insert(
            UserEntity(
                0,
                firstName,
                lastName,
                email
            )
        )
    }

    override fun getUserByName(name: String): Single<UserEntity> {
        return local.getUserByName(name)
    }

    override fun delete(firstName: String) {
        local.getUserByName(firstName)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    local.delete(UserEntity(0, it.firstName, it.lastName, it.email))
                },
                onError = {
                    Helper.toastShort("User with this name not found")
                    Log.d(Constants.LOG_IN_TAG, "getUserByName() e -> $it")
                }
            )
    }
}