package com.raouf.ecommerceapp

import android.app.Application
import com.raouf.data.di.dataModule
import com.raouf.domain.di.domainModule
import com.raouf.ecommerceapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EcommerceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EcommerceApp)
            modules(
                dataModule, domainModule, presentationModule,
            )
        }
    }
}