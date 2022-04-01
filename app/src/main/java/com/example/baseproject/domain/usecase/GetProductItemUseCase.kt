package com.example.baseproject.domain.usecase

import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.data.repository.Repository
import com.example.baseproject.domain.UseCase

internal class GetProductItemUseCase(
    private val repository: Repository
): UseCase {
    suspend operator fun invoke(productId: Long): ProductResponse? {
        return repository.getProductItem(productId)
    }
}