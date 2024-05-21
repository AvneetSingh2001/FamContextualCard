package com.avneet.famcontextualcard.models

import com.avneet.famcontextualcard.models.Entity
import com.google.gson.annotations.SerializedName

data class FormattedText (
    val text: String? = null,

    @SerializedName("entities")
    val entityList: List<Entity>? = null
)