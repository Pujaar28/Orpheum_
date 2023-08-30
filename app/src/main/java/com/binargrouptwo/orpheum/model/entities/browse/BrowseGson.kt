package com.binargrouptwo.orpheum.model.entities.browse

import com.google.gson.annotations.SerializedName

//Featured Playlist
data class FeaturedItems(
    @SerializedName("description")
    val descriptions: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    val images: ArrayList<Images>,
    @SerializedName("name")
    val name: String,
    @SerializedName("uri")
    val uri: String
)

data class Images(
    @SerializedName("url")
    val url: String
)

data class ResponseData(
    @SerializedName("artists")
    val data: Data
)

data class ResponseTracks(
    @SerializedName("tracks")
    val tracks: ArrayList<Track>)

data class Data(
    @SerializedName("items")
    val artists: ArrayList<Artist>)

data class Artist(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("followers")
    val follower: Follower,
    @SerializedName("images")
    val images: ArrayList<Image>)

data class Follower(
    @SerializedName("total")
    val total: Int)

data class Image(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int)

data class Track(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("disc_number")
    val number: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String = "",
    @SerializedName("album")
    val album: Album
)

data class Album(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val year: String,
    @SerializedName("images")
    val images: ArrayList<Image>)