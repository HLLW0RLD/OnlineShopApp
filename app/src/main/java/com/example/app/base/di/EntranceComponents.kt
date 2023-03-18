package com.example.app.base.di

import com.example.app.base.main.BaseApp
import com.example.core.db.UserDAO
import com.example.core.repository.ILocalRepository
import com.example.core.repository.LocalRepository
import org.koin.dsl.module

class EntranceComponents {

    val coreModule = module {

        single<ILocalRepository> {
            LocalRepository(get())
        }

        single<UserDAO> {
            BaseApp.getUserDAO()
        }
    }
}