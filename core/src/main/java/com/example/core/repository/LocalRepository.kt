package com.example.core.repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.core.db.UserDAO
import com.example.core.db.UserEntity
import com.example.utils.extentions.Constants
import com.example.utils.extentions.Helper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.coroutines.coroutineContext

class LocalRepository(val dao: UserDAO): ILocalRepository {

    override fun insert(firstName: String, lastName: String, email: String) {
        dao.insert(
            UserEntity(
                0,
                firstName,
                lastName,
                email
            )
        )
    }

    override fun getUserByName(name: String): Single<UserEntity> {
        return dao.getUserByName(name)
    }

    override fun delete(firstName: String) {
        dao.getUserByName(firstName)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    dao.delete(UserEntity(0, it.firstName, it.lastName, it.email))
                },
                onError = {
                    Log.d(Constants.LOG_IN_TAG, "getUserByName() e -> $it")
                }
            )
    }
}