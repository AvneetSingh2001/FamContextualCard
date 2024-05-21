package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class Gradient(
    val angle: Double? = null,

    @SerializedName("colors")
    val colorList: List<String>? = null
)
