package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class Card(
    val id: Int,

    val name: String,

    val title: String? = null,

    val url: String? = null,

    val description: String? = null,

    val icon: CardImage? = null,

    @SerializedName("formatted_title")
    val formattedTitle: FormattedText? = null,

    @SerializedName("bg_image")
    val bgImage: CardImage? = null,

    @SerializedName("cta")
    val ctaList: List<Cta>? = null,

    @SerializedName("formatted_description")
    val formattedDescription: FormattedText? = null,

    @SerializedName("bg_color")
    val bgColor: String? = null,

    @SerializedName("bg_gradient")
    val bgGradient: Gradient? = null
)