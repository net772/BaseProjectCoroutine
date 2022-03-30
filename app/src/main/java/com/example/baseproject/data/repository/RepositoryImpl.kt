package com.example.baseproject.data.repository

import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.data.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val productApi: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun getProductList(): List<ProductResponse> = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalProductList(): List<ProductResponse> = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductItem(ProductItem: ProductResponse): Long = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductList(ProductList: List<ProductResponse>) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductItem(ProductItem: ProductResponse) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductItem(itemId: Long): ProductResponse? = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductItem(id: Long) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }
}