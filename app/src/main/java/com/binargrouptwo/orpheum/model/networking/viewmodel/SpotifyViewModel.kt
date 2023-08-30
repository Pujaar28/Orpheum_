package com.binargrouptwo.orpheum.model.networking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.binargrouptwo.orpheum.constants.Result
import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.playback.PlaybackResponse
import com.binargrouptwo.orpheum.model.entities.profile.ProfileResponse
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepo
import kotlinx.coroutines.CoroutineExceptionHandler

class SpotifyViewModel (
    private val spotifyRepo: SpotifyRepo
) : ViewModel() {
    //Live Data Response
    //Browse
    private var newReleaseResponse = MutableLiveData(NewReleaseResponse())
    private var featuredPlaylistResponse = MutableLiveData(FeaturedPlaylistResponse())
    private var playlistResponse = MutableLiveData(PlaylistResponse())
    //Playback
    private var playback = MutableLiveData(PlaybackResponse())
    //Profile
    private var profileResponse = MutableLiveData<ProfileResponse?>()
    private var tracksResponse = MutableLiveData<TrackResponse?>()

    //empty case
    var isEmptyCase = MutableLiveData(false)

    //failure case
    var isFailureCaseMessage = MutableLiveData("")

    //loading case
    var isLoadingCase = MutableLiveData(false)

    //Function from LiveData List
    //Browse
    fun getNewRelease(): MutableLiveData<NewReleaseResponse> {
        return newReleaseResponse
    }

    fun getFeaturedPlaylist(): MutableLiveData<FeaturedPlaylistResponse>{
        return featuredPlaylistResponse
    }
    //Library
    fun getUserPlaylist():MutableLiveData<PlaylistResponse>{
        return playlistResponse
    }

    //Playback
    fun getPlayback(): MutableLiveData<PlaybackResponse>{
        return playback
    }
    fun getTrack(): MutableLiveData<TrackResponse?> {
        return tracksResponse
    }

    //Profile
    fun getProfile(): MutableLiveData<ProfileResponse?> {
        return profileResponse
    }

    /*--------onSuccess---------*/
    //Browse
    fun onFeaturedPlaylist() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getFeaturedPlaylist()
            when (response) {
                is Result.Success -> {
                    featuredPlaylistResponse.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }
    fun onNewRelease() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getNewRelease()
            when (response) {
                is Result.Success -> {
                    newReleaseResponse.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }
    //Library
    fun onUserPlaylist() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getUserPlaylist()
            when (response) {
                is Result.Success -> {
                    playlistResponse.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }

    fun onUserTrack() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getTrack()
            when (response) {
                is com.binargrouptwo.orpheum.constants.Result.Success -> {
                    tracksResponse.postValue(response.value)
                }
                is com.binargrouptwo.orpheum.constants.Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }

    fun onLoadData() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getUserPlaylist()
            when (response) {
                is Result.Success -> {
                    playlistResponse.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }

    //Playback
    fun onPlayback(){
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getPlayback()
            when (response) {
                is Result.Success -> {
                    playback.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }

    fun onPlayerPlay() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            spotifyRepo.putPlay()
        }
    }

    fun onPlayerPause() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            spotifyRepo.putPause()
        }
    }
    //Profile
    fun onLoadProfile() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getProfile()
            when (response) {
                is com.binargrouptwo.orpheum.constants.Result.Success -> {
                    profileResponse.postValue(response.value)
                }
                is com.binargrouptwo.orpheum.constants.Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }
}