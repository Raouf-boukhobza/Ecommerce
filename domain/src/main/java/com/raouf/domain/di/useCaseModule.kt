package com.raouf.domain.di

import com.raouf.domain.useCase.GetProductById
import com.raouf.domain.useCase.GetProductsUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory {
        GetProductsUseCase(get())
    }
    factory {
        GetProductById(get())
    }
}