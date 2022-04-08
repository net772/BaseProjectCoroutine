package com.example.baseproject.data.repository

import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val productApi: ApiService
) : Repository {
    override fun getProductList(): Flow<List<ProductResponse>> = flow {
        emit(productApi.getProducts())
    }.map {
        it.items
    }

    override suspend fun getLocalProductList(): List<ProductResponse>  {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductItem(ProductItem: ProductResponse): Long  {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductList(ProductList: List<ProductResponse>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductItem(ProductItem: ProductResponse) {
        TODO("Not yet implemented")
    }

    override fun getProductItem(itemId: Long): Flow<ProductResponse> = flow {
        emit(productApi.getProduct(itemId))
    }.map {
        it
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductItem(id: Long)  {
        TODO("Not yet implemented")
    }
}