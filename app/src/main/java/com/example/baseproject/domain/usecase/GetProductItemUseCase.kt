package com.example.baseproject.domain.usecase

import com.example.baseproject.data.repository.Repository
import com.example.baseproject.domain.UseCase

class GetProductItemUseCase(
    private val repository: Repository
): UseCase {
    suspend operator fun invoke(productId: Long) = repository.getProductItem(productId)

}