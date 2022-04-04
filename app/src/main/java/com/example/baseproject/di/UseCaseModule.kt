package com.example.baseproject.di

import com.example.baseproject.domain.usecase.GetProductItemUseCase
import com.example.baseproject.domain.usecase.GetProductListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }
}