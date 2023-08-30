package com.binargrouptwo.orpheum.model.networking.repository

import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.playback.PlaybackResponse
import com.binargrouptwo.orpheum.model.entities.profile.ProfileResponse
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse
import com.binargrouptwo.orpheum.model.networking.APIClient

class SpotifyRepoManager {
    private val apiClient = APIClient.instance

    suspend fun getNewRelease(): NewReleaseResponse = apiClient.getNewRelease()

    suspend fun getFeaturedPlaylist(): FeaturedPlaylistResponse = apiClient.getFeaturedPlaylist()

    suspend fun getPlayback(): PlaybackResponse = apiClient.getCurrentlyPlaying()

    suspend fun putPlay() = apiClient.putPlay()

    suspend fun putPause() = apiClient.putPause()

    //Lib
    suspend fun getUserPlaylist(): PlaylistResponse = apiClient.getPlaylist()

    suspend fun getProfile(): ProfileResponse = apiClient.getProfile()

    suspend fun getTrack(): TrackResponse = apiClient.getTrack()
}