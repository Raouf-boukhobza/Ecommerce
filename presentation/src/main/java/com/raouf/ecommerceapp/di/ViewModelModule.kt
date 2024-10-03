package com.raouf.ecommerceapp.di

import com.raouf.ecommerceapp.ui.detail.DetailScreenViewModel
import com.raouf.ecommerceapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailScreenViewModel(get())
    }
}