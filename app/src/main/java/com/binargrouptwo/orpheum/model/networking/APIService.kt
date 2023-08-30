package com.binargrouptwo.orpheum.model.networking

import android.database.Observable
import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.binargrouptwo.orpheum.model.entities.browse.ResponseData
import com.binargrouptwo.orpheum.model.entities.browse.ResponseTracks
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.playback.PlaybackResponse
import com.binargrouptwo.orpheum.model.entities.profile.ProfileResponse
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @Headers(value = ["Accept: application/json", "Content-Type: application/json"])
    @GET(value = "search")
    fun getArtists(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("market") market: String,
        @Query("limit") limit: Int
    ): Observable<ResponseData>

    //https://api.spotify.com/v1/artists/{id}/top-tracks
    @Headers(value = ["Accept: application/json", "Content-Type: application/json"])
    @GET(value = "artists/{id}/top-tracks")
    fun getTopTracks(
        @Path("id") artistId: String,
        @Query("country") country: String
    ): Observable<ResponseTracks>

    //Browse
    @GET(value = "browse/new-releases")
    suspend fun getNewRelease(): NewReleaseResponse

    @GET(value = "browse/featured-playlists")
    suspend fun getFeaturedPlaylist(): FeaturedPlaylistResponse

    //Playback
    @GET(value = "me/player/currently-playing")
    suspend fun getCurrentlyPlaying(): PlaybackResponse

    @PUT(value = "me/player/play")
    suspend fun putPlay()
    @PUT(value = "me/player/pause")
    suspend fun putPause()

    //Library
    @GET(value = "me/playlists")
    suspend fun getPlaylist(): PlaylistResponse

    @GET(value = "me")
    suspend fun getProfile() : ProfileResponse

    @GET(value = "me/tracks")
    suspend fun getTrack() : TrackResponse
}