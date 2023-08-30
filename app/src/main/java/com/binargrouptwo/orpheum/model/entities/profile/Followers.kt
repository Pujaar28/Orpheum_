package com.binargrouptwo.orpheum.model.entities.profile


import com.google.gson.annotations.SerializedName

data class Followers(
    @SerializedName("href")
    val href: Any,
    @SerializedName("total")
    val total: Int
)