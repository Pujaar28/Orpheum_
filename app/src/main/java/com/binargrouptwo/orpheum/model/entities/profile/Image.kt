package com.binargrouptwo.orpheum.model.entities.profile


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("height")
    val height: Any,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Any
)