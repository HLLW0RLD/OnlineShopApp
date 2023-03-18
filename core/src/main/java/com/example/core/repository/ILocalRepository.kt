package com.example.core.repository

import com.example.core.db.UserEntity
import io.reactivex.rxjava3.core.Single

interface ILocalRepository {

    fun insert(firstName: String, lastName: String, email: String)

    fun getUserByName(name: String): Single<UserEntity>

    fun delete(firstName: String)
}