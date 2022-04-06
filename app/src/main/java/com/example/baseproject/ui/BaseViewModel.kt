package com.example.baseproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseproject.state.ResultState
import com.example.baseproject.utility.config.KoinConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

/**
 * @see onResult 단일 RESTFul API 를 호출 및 결과를 받기위한 함수
 * @see onResultZip 두 개의 RESTFul API 를 호출 및 결과를 동시에 받기위한 함수
 */

abstract class BaseViewModel(
    private val app: Application
): AndroidViewModel(app), KoinComponent {

    abstract fun fetchData(): Job

    protected val ioDispatcher: CoroutineDispatcher by inject(named(KoinConstants.DISPATCHER_IO))

    protected fun <T> Flow<T>.onResult(collect: (T) -> Unit) {
        flowOn(ioDispatcher).onEach { item ->
            collect.invoke(item)
        }.launchIn(viewModelScope)
    }

    protected fun <T> Flow<T>.onState(collect: (ResultState<T>) -> Unit) {
        flowOn(ioDispatcher)
            .onCompletion { collect(ResultState.Finish) }
            .onStart { collect(ResultState.Loading) }
            .catch { collect(ResultState.Error(it)) }
            .onEach { collect(ResultState.Success(it)) }
            .launchIn(viewModelScope)
    }
}