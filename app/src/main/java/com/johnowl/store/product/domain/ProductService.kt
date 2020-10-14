package com.johnowl.store.product.domain

import com.johnowl.store.product.repository.ProductRepository
import javax.inject.Inject

class ProductService @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun listCategories(): List<Category> {
        return productRepository.listCategories()
    }

    suspend fun listBrands(): List<Brand> {
        return productRepository.listBrands()
    }
}