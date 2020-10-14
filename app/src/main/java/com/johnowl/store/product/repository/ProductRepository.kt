package com.johnowl.store.product.repository

import com.johnowl.store.product.domain.Brand
import com.johnowl.store.product.network.ProductRetrofit
import com.johnowl.store.product.domain.Category
import com.johnowl.store.product.mapper.BrandListResponseMapper
import com.johnowl.store.product.mapper.CategoryListResponseMapper
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRetrofit: ProductRetrofit,
    private val categoryListResponseMapper: CategoryListResponseMapper,
    private val brandListResponseMapper: BrandListResponseMapper
) {

    suspend fun listCategories(): List<Category> {
        val response = productRetrofit.listCategories()
        return categoryListResponseMapper.mapFromEntity(response)
    }

    suspend fun listBrands(): List<Brand> {
        val response = productRetrofit.listBrands()
        return brandListResponseMapper.mapFromEntity(response)
    }
}