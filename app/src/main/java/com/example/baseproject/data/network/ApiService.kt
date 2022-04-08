package com.example.baseproject.data.network

import com.example.baseproject.data.Response.ProductResponse
import com.example.baseproject.data.Response.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse

    @GET("products/{productId}")
    suspend fun getProduct(@Path("productId") productId: Long): ProductResponse
}