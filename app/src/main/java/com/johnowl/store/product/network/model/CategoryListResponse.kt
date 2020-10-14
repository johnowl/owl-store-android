package com.johnowl.store.product.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryListResponse(

    @Expose
    @SerializedName("items")
    var items: List<CategoryResponse>
)