package com.johnowl.store.product.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BrandResponse(

    @Expose
    @SerializedName("id")
    var id: String,

    @Expose
    @SerializedName("name")
    var name: String
)