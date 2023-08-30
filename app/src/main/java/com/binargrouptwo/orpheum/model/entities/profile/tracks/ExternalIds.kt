package com.binargrouptwo.orpheum.model.entities.profile.tracks


import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("isrc")
    val isrc: String
)