package com.johnowl.store.product.mapper

import com.johnowl.store.app.util.EntityMapper
import com.johnowl.store.product.domain.Category
import com.johnowl.store.product.network.model.CategoryResponse
import javax.inject.Inject

class CategoryResponseMapper @Inject constructor(): EntityMapper<CategoryResponse, Category> {
    override fun mapFromEntity(entity: CategoryResponse): Category {
        return Category(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Category): CategoryResponse {
        return CategoryResponse(
            id = domainModel.id,
            name = domainModel.name
        )
    }
}