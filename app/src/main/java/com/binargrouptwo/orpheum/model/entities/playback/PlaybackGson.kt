package com.binargrouptwo.orpheum.model.entities.playback

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("artists")
    val artists: ArrayList<Artists>,
    @SerializedName("name")
    val name: String,
    @SerializedName("images")
    val images: ArrayList<Images>
)

data class Artists(
    @SerializedName("name")
    val name: String
)

data class Images(
    @SerializedName("url")
    val url: String
)