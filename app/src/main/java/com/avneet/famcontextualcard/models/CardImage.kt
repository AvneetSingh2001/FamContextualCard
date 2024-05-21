package com.avneet.famcontextualcard.models

import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("image_type")
    val imageType: String? = null,

    @SerializedName("asset_type")
    val assetType: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,
)
