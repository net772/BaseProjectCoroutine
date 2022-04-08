package com.example.baseproject.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.domain.usecase.GetProductItemUseCase
import com.example.baseproject.state.ResultState
import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel (
    private val productId: String,
    app: Application,
    private val getProductItemUseCase: GetProductItemUseCase
): BaseViewModel(app) {

    private val _productDetailState = MutableStateFlow<ResultState<ProductResponse>>(ResultState.UnInitialize)
    val productDetailState = _productDetailState.asStateFlow()

    override fun fetchData(): Job = viewModelScope.launch {
        getProductItemUseCase.invoke(productId.toLong())?.onState {
            _productDetailState.value = it
        }
    }
}
