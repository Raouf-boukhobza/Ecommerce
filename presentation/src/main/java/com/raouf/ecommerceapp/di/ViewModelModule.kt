package com.raouf.ecommerceapp.di

import com.raouf.ecommerceapp.ui.home.HomeViewModel
import org.koin.dsl.module
import kotlin.math.sin

val viewModelModule = module {
    single {
        HomeViewModel(get())
    }
}