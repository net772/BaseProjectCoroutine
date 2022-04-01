package com.example.baseproject.di

import com.example.baseproject.data.repository.Repository
import com.example.baseproject.data.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {  RepositoryImpl(get(), get()) } //// <반환 타입>
}