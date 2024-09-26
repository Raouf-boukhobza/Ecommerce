package com.raouf.data.di

import com.raouf.data.repository.ProductRepositoryImpl
import com.raouf.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository>{ ProductRepositoryImpl(get())}
}