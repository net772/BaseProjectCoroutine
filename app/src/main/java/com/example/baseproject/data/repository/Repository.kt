package com.example.baseproject.data.repository

import com.example.baseproject.data.Response.ProductResponse

interface Repository {
    suspend fun getProductList(): List<ProductResponse>

    suspend fun getLocalProductList(): List<ProductResponse>

    suspend fun insertProductItem(ProductItem: ProductResponse): Long

    suspend fun insertProductList(ProductList: List<ProductResponse>)

    suspend fun updateProductItem(ProductItem: ProductResponse)

    suspend fun getProductItem(itemId: Long): ProductResponse?

    suspend fun deleteAll()

    suspend fun deleteProductItem(id: Long)
}