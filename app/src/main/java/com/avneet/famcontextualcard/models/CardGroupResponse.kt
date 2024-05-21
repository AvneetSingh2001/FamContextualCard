package com.avneet.famcontextualcard.models

import com.google.gson.annotations.SerializedName

class CardGroupResponse (
    @SerializedName("hc_groups")
    val cardGroup: List<CardGroup>
)