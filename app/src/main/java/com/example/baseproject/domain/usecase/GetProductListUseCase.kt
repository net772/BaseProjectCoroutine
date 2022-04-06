package com.example.baseproject.domain.usecase

import com.example.baseproject.data.repository.Repository
import com.example.baseproject.domain.UseCase

class GetProductListUseCase(
    private val productRepository: Repository
) : UseCase {
    suspend operator fun invoke() = productRepository.getProductList()
}