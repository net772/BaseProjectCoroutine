package com.example.baseproject.data.Response

data class ProductsResponse(
    val items: List<ProductResponse>,
    val count: Int
)