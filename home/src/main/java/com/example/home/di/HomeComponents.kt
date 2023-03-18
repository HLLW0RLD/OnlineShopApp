package com.example.home.di

import com.example.api.remote.ProductApi
import com.example.api.remote.ProductInterface
import com.example.api.repository.IRemoteRepository
import com.example.api.repository.RemoteRepository
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://run.mocky.io/v3/"
class HomeComponents {

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

}