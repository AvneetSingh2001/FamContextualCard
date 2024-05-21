package com.avneet.famcontextualcard.models

import com.google.gson.annotations.SerializedName

data class Entity(
    val text: String,

    @SerializedName("font_style")
    val fontStyle: String? = null,

    val color: String? = null,

    val url: String? = null,
) {
    enum class FontStyle {
        @SerializedName("underline")
        UNDERLINE,

        @SerializedName("italic")
        ITALIC,

        @SerializedName("bold")
        BOLD,
    }
}