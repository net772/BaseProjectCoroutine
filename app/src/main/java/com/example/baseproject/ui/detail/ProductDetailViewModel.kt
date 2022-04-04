package com.example.baseproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.domain.usecase.GetProductItemUseCase
import com.example.baseproject.ui.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductDetailViewModel (
    private val productId: Long,
    private val getProductItemUseCase: GetProductItemUseCase
): BaseViewModel() {

    private var _productDetailState = MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailState: LiveData<ProductDetailState> = _productDetailState


    override fun fetchData(): Job = viewModelScope.launch {
        setState(ProductDetailState.Loading)
        getProductItemUseCase(productId)?.let { product ->
            setState(
                ProductDetailState.Success(product)
            )
        } ?: kotlin.run {
            setState(ProductDetailState.Error)
        }
    }



    private fun setState(state: ProductDetailState) {
        _productDetailState.postValue(state)
    }
}
