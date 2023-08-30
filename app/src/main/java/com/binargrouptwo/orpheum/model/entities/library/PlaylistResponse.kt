package com.binargrouptwo.orpheum.model.entities.library


/*
data class PlaylistResponse(
    val playlist: Playlist? = null
)
*/

data class PlaylistResponse(
    val href: String? = null,
    val items: ArrayList<PlaylistItem>? = null,
    val limit: Int? = null,
    val next: Any? = null,
    val offset: Int? = null,
    val previous: Any? = null,
    val total: Int? = null
)

