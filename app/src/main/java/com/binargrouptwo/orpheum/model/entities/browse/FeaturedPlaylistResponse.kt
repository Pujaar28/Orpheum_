package com.binargrouptwo.orpheum.model.entities.browse

data class FeaturedPlaylistResponse(
    val playlists: Playlists? = null
)

data class Playlists(
    val href: String,
    val items: List<FeaturedItems>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)