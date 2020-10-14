package com.johnowl.store.product.mapper

import com.johnowl.store.app.util.EntityMapper
import com.johnowl.store.product.domain.Brand
import com.johnowl.store.product.network.model.BrandResponse
import javax.inject.Inject

class BrandResponseMapper @Inject constructor(): EntityMapper<BrandResponse, Brand> {
    override fun mapFromEntity(entity: BrandResponse): Brand {
        return Brand(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Brand): BrandResponse {
        return BrandResponse(
            id = domainModel.id,
            name = domainModel.name
        )
    }
}