package com.binargrouptwo.orpheum.model.entities.browse

data class NewReleaseResponse(
    val albums: Albums? = null
)

data class Albums(
    val href: String,
    val items: List<ItemData>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class ItemData(
    val album_type: String,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
)

data class ExternalUrlsX(
    val spotify: String
)

data class ImageData(
    val height: Int,
    val url: String,
    val width: Int
)