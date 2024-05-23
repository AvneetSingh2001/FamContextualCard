package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class Gradient(
    val angle: Float? = null,

    @SerializedName("colors")
    val colorList: List<String>? = null
)
