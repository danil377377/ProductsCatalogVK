package com.example.productscatalogvk.di

import com.example.productscatalogvk.ui.presentation.ProductsSearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProductsSearchViewModel(androidApplication(), get())
    }
}
