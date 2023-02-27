package com.example.core.repository

import com.example.api.data.UserData
import com.example.core.db.UserDAO
import com.example.core.db.UserEntity
import io.reactivex.rxjava3.core.Single

class LocalRepository(val dao: UserDAO): ILocalRepository {

    override fun insert(data: UserData) {
        dao.insert(
            UserEntity(
                data.firstName,
                data.lastName,
                data.email
            )
        )
    }

    override fun getUserByName(name: String): Single<UserEntity> {
        return dao.getUserByName(name)
    }

    override fun delete(data: UserData) {
        dao.delete(
            UserEntity(
                data.firstName,
                data.lastName,
                data.email
            )
        )
    }
}