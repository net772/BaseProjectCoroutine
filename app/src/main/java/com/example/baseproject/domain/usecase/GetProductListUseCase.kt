package com.example.baseproject.domain.usecase

import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.data.repository.Repository
import com.example.baseproject.domain.UseCase

class GetProductListUseCase(
    private val productRepository: Repository
) : UseCase {

    suspend operator fun invoke(): List<ProductResponse> {
        return productRepository.getProductList()
    }
}