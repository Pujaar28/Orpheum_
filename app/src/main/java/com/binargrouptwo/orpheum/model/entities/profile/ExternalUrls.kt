package com.binargrouptwo.orpheum.model.entities.profile


import com.google.gson.annotations.SerializedName

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)