package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("image_type")
    val imageType: ImageType,

    @SerializedName("asset_type")
    val assetType: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("aspect_ratio")
    val aspectRatio: Float? = null
) {
    enum class ImageType {
        @SerializedName("ext")
        EXTERNAL,

        @SerializedName("asset")
        ASSET
    }
}
