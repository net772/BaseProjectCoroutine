package com.example.baseproject.ui.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.domain.usecase.GetProductListUseCase
import com.example.baseproject.state.ResultState
import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    app: Application,
    private val getProductListUseCase: GetProductListUseCase
) : BaseViewModel(app) {

    private val _productListStateLiveData = MutableStateFlow<ResultState<List<ProductResponse>>>(ResultState.UnInitialize)
    val productListStateLiveData = _productListStateLiveData.asStateFlow()

    override fun fetchData(): Job = viewModelScope.launch {
        getProductListUseCase.invoke().onState {
            _productListStateLiveData.value = it
        }
    }
}