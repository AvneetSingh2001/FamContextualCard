package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class Cta (
    val text: String? = null,

    val url: String? = null,

    @SerializedName("bg_color")
    val bgColor: String? = null,

    @SerializedName("text_color")
    val textColor: String? = null,
)
