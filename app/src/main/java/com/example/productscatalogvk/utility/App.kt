package com.example.productscatalogvk.utility

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.productscatalogvk.di.interactorModule
import com.example.productscatalogvk.di.repositoryModule
import com.example.productscatalogvk.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext

class App : Application(), KoinComponent {
    companion object {
        private const val LIMIT = 20

    }
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(interactorModule, repositoryModule, viewModelModule)
        }

    }

}