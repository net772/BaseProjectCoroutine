package com.example.baseproject.ui.detail

import com.example.baseproject.data.Response.ProductResponse

sealed class ProductDetailState {
    object UnInitialized: ProductDetailState()

    object Loading: ProductDetailState()

    data class Success(
        val productEntity: ProductResponse
    ): ProductDetailState()

    object Order: ProductDetailState()

    object Error: ProductDetailState()
}