package com.johnowl.store.product.mapper

import com.johnowl.store.app.util.EntityMapper
import com.johnowl.store.product.domain.Category
import com.johnowl.store.product.network.model.CategoryListResponse
import javax.inject.Inject

class CategoryListResponseMapper @Inject constructor(
    private val categoryResponseMapper: CategoryResponseMapper
):
    EntityMapper<CategoryListResponse, List<Category>> {

    override fun mapFromEntity(entity: CategoryListResponse): List<Category> {
        return entity.items.map { categoryResponseMapper.mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: List<Category>): CategoryListResponse {
        return CategoryListResponse(
            items = domainModel.map { categoryResponseMapper.mapToEntity(it) }
        )
    }
}