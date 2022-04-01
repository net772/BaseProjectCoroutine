package com.example.baseproject.di

import com.example.baseproject.domain.usecase.GetProductItemUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductItemUseCase(get()) }
}