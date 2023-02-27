package com.example.core.repository

import com.example.api.data.UserData
import com.example.core.db.UserEntity
import io.reactivex.rxjava3.core.Single

interface ILocalRepository {

    fun insert(data: UserData)

    fun getUserByName(name: String): Single<UserEntity>

    fun delete(data: UserData)
}