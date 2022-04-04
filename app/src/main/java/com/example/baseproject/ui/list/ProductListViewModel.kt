package com.example.baseproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.domain.usecase.GetProductListUseCase
import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductListViewModel(
    val getProductListUseCase: GetProductListUseCase
) : BaseViewModel() {

    private var _productListStateLiveData = MutableLiveData<ProductListState>(ProductListState.UnInitialized)
    val productListStateLiveData: LiveData<ProductListState> = _productListStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _productListStateLiveData.postValue(ProductListState.Loading)
        _productListStateLiveData.postValue(ProductListState.Success(getProductListUseCase()))
    }
}