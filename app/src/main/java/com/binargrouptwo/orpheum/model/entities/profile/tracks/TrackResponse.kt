package com.binargrouptwo.orpheum.model.entities.profile.tracks


import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)