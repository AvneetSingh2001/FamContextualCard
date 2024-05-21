package com.avneet.famcontextualcard.data.models

import com.google.gson.annotations.SerializedName

class CardGroupResponse (
    @SerializedName("hc_groups")
    val cardGroup: List<CardGroup>
)