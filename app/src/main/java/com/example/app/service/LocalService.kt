package com.example.app.service

import com.example.app.domain.ILocalService
import com.example.core.db.UserEntity
import com.example.core.repository.ILocalRepository
import io.reactivex.rxjava3.core.Single

class LocalService(private val local: ILocalRepository) : ILocalService {

    override fun insert(firstName: String, lastName: String, email: String) {
        local.insert(UserEntity(
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