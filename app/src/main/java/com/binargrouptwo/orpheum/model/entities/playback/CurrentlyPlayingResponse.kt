package com.binargrouptwo.orpheum.model.entities.playback


data class PlaybackResponse(
    val album: List<Album>? = null,
    val artists: List<Artists>? = null
)

