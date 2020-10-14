package com.johnowl.store.product.mapper

import com.johnowl.store.app.util.EntityMapper
import com.johnowl.store.product.domain.Brand
import com.johnowl.store.product.network.model.BrandListResponse
import javax.inject.Inject

class BrandListResponseMapper @Inject constructor(
    private val BrandResponseMapper: BrandResponseMapper
):
    EntityMapper<BrandListResponse, List<Brand>> {

    override fun mapFromEntity(entity: BrandListResponse): List<Brand> {
        return entity.items.map { BrandResponseMapper.mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: List<Brand>): BrandListResponse {
        return BrandListResponse(
            items = domainModel.map { BrandResponseMapper.mapToEntity(it) }
        )
    }
}