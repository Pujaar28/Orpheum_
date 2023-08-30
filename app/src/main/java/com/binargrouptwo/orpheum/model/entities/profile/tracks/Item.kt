package com.binargrouptwo.orpheum.model.entities.profile.tracks


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("track")
    val track: Track
)