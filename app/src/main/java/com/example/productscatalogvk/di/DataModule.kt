package com.example.productscatalogvk.di

import android.app.Application
import com.example.productscatalogvk.data.network.DumApi
import com.example.productscatalogvk.data.network.GlideLoaderImpl
import com.example.productscatalogvk.data.network.NetworkClient
import com.example.productscatalogvk.data.network.ProductsRepositoryImpl
import com.example.productscatalogvk.data.network.RetrofitNetworkClient
import com.example.productscatalogvk.domain.api.GlideLoader
import com.example.productscatalogvk.domain.api.ProductsRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    factory<ProductsRepository> {
        ProductsRepositoryImpl(get())
    }
    single<DumApi> {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DumApi::class.java)
    }


    single<NetworkClient> {
        RetrofitNetworkClient(androidContext(), get())
    }

    factory { Gson() }
    factory<GlideLoader> {
        GlideLoaderImpl()
    }
}