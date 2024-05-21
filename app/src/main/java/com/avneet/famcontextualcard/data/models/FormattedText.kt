package com.avneet.famcontextualcard.data.models

import com.avneet.famcontextualcard.data.models.Entity
import com.google.gson.annotations.SerializedName

data class FormattedText (
    val text: String? = null,

    @SerializedName("entities")
    val entityList: List<Entity>? = null
)