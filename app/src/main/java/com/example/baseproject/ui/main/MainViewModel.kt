package com.example.baseproject.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    app: Application
): BaseViewModel(app) {
    override fun fetchData(): Job = Job()

    private var _mainStateLiveData = MutableLiveData<MainState>()
    val mainStateLiveData: LiveData<MainState> = _mainStateLiveData

    fun refreshOrderList() = viewModelScope.launch {
        _mainStateLiveData.postValue(MainState.RefreshOrderList)
    }
}