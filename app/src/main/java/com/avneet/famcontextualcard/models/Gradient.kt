package com.avneet.famcontextualcard.models

import com.google.gson.annotations.SerializedName

data class Gradient(
    val angle: Double? = null,

    @SerializedName("colors")
    val colorList: List<String>? = null
)
