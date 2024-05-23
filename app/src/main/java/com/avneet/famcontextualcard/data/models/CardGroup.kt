package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

data class CardGroup(
    val id: Int? = null,

    val name: String? = null,

    val height: Int? = null,

    val slug: String? = null,

    val level: Int? = null,

    @SerializedName("card_type")
    val cardType: Int? = null,

    @SerializedName("design_type")
    val designType: DesignType,

    @SerializedName("cards")
    val cardList: List<Card>,

    @SerializedName("is_scrollable")
    val isScrollable: Boolean = false,

    @SerializedName("is_full_width")
    val isFullWidth: Boolean = false
) {
    enum class DesignType {
        @SerializedName("HC1")
        SMALL_DISPLAY_CARD,

        @SerializedName("HC3")
        BIG_DISPLAY_CARD,

        @SerializedName("HC5")
        IMAGE_CARD,

        @SerializedName("HC6")
        SMALL_CARD_WITH_ARROW,

        @SerializedName("HC9")
        DYNAMIC_WIDTH_CARD
    }
}