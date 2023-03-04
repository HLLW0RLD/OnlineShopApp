package com.example.app.di

import com.example.api.remote.ProductApi
import com.example.api.remote.ProductInterface
import com.example.api.repository.IRemoteRepository
import com.example.api.repository.RemoteRepository
import com.example.app.base.BaseApp
import com.example.app.domain.ILocalService
import com.example.app.domain.IRemoteService
import com.example.app.service.LocalService
import com.example.app.service.RemoteService
import com.example.core.db.UserDAO
import com.example.core.repository.ILocalRepository
import com.example.core.repository.LocalRepository
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://run.mocky.io/v3/"
class AppComponents {

    val appModule = module {

        single<IRemoteService> {
            RemoteService(get())
        }

        single<ILocalService> {
            LocalService(get())
        }
    }

    val apiModule = module {

        single<IRemoteRepository> {
            RemoteRepository(ProductApi(get()))
        }

        single<ProductInterface> {
            get<Retrofit>()
                .create(ProductInterface::class.java)
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(get())
                .build()
        }
        factory<Converter.Factory> {
            GsonConverterFactory
                .create(
                    GsonBuilder()
                    .setLenient()
                    .create()
                )
        }
    }

    val coreModule = module {

        single<ILocalRepository> {
            LocalRepository(get())
        }

        single<UserDAO> {
            BaseApp.getUserDAO()
        }
    }
}