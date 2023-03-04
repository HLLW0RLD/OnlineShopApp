package com.example.core.repository

import com.example.core.db.UserDAO
import com.example.core.db.UserEntity
import io.reactivex.rxjava3.core.Single

/*TODO make reactive after success testing of actual insert() and delete()*/
class LocalRepository(val dao: UserDAO): ILocalRepository {

    override fun insert(data: UserEntity) {
        dao.insert(
            UserEntity(
                0,
                data.firstName,
                data.lastName,
                data.email
            )
        )
    }

    override fun getUserByName(name: String): Single<UserEntity> {
        return dao.getUserByName(name)
    }

    override fun delete(data: UserEntity) {
        dao.delete(
            UserEntity(
                0,
                data.firstName,
                data.lastName,
                data.email
            )
        )
    }
}