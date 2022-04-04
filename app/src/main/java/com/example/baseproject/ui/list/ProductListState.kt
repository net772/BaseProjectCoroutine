package com.example.baseproject.ui.list

import com.example.baseproject.data.Response.ProductResponse

sealed class ProductListState {

    object UnInitialized: ProductListState()

    object Loading: ProductListState()

    data class Success(
        val productList: List<ProductResponse>
    ): ProductListState()

    object Error: ProductListState()
}