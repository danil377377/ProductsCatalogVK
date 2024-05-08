package com.example.productscatalogvk.di

import com.example.productscatalogvk.domain.api.ProductsInteractor
import com.example.productscatalogvk.domain.impl.ProductsInteractorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val interactorModule = module {
    factory<ProductsInteractor> {
        ProductsInteractorImpl(get())
    }
}
