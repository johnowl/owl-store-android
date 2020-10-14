package com.johnowl.store.product.network

import com.johnowl.store.product.network.model.BrandListResponse
import com.johnowl.store.product.network.model.CategoryListResponse
import retrofit2.http.GET

interface ProductRetrofit {

    @GET("products/categories")
    suspend fun listCategories(): CategoryListResponse

    @GET("products/brands")
    suspend fun listBrands(): BrandListResponse

}