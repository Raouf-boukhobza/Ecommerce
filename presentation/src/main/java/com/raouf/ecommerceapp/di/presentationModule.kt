package com.raouf.ecommerceapp.di

import org.koin.dsl.module


val presentationModule = module {
  includes(viewModelModule)
}