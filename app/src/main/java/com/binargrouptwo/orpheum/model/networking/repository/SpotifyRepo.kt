package com.binargrouptwo.orpheum.model.networking.repository

import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.binargrouptwo.orpheum.constants.Result
import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.playback.PlaybackResponse
import com.binargrouptwo.orpheum.model.entities.profile.ProfileResponse
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse

class SpotifyRepo(private val spotifyRepoManager: SpotifyRepoManager) {
    suspend fun getNewRelease(): Result<NewReleaseResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getNewRelease()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getFeaturedPlaylist(): Result<FeaturedPlaylistResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getFeaturedPlaylist()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getPlayback(): Result<PlaybackResponse, Exception>{
        return try {
            val response = spotifyRepoManager.getPlayback()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun putPlay(){
        spotifyRepoManager.putPlay()
    }

    suspend fun putPause(){
        spotifyRepoManager.putPause()
    }

    //Library
    suspend fun getUserTrack(): Result<TrackResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getTrack()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getUserPlaylist(): Result<PlaylistResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getUserPlaylist()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getProfile(): Result<ProfileResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getProfile()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }

    suspend fun getTrack(): Result<TrackResponse, Exception> {
        return try {
            val response = spotifyRepoManager.getTrack()
            Result.build { response }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }
}