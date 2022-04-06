package com.example.baseproject

import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.baseproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: Application(), LifecycleObserver {

    companion object {
        private var instance: Application? = null
        val applicationContext: Context get() = instance!!.applicationContext
        var isAppOnForeground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        isAppOnForeground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        isAppOnForeground = true
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(
                coroutineModule,
                networkModule,
                useCaseModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}