package com.binargrouptwo.orpheum.model.entities.library
import com.google.gson.annotations.SerializedName


data class PlaylistItem(
    @SerializedName("description")
    val description: String,/*
    @SerializedName("external_urls")
    val externalUrls: List<ExternalUrls>,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: ArrayList<Image>,*/
    @SerializedName("name")
    val name: String/*,
    @SerializedName("tracks")
    val tracks: ArrayList<Tracks>,
    @SerializedName("uri")
    val uri: String*/
)

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)

data class Image(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class Owner(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

data class Tracks(
    @SerializedName("href")
    val href: String,
    @SerializedName("total")
    val total: Int
)