package com.binargrouptwo.orpheum.ui.playmusic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binargrouptwo.orpheum.model.entities.playback.PlaybackResponse
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayMusicViewModel(private val spotifyRepo: SpotifyRepo): ViewModel() {

    private var trackResponse = MutableLiveData<TrackResponse?>()
    private var playback = MutableLiveData(PlaybackResponse())

    var isEmptyCase = MutableLiveData(false)
    var isFailurCaseMessage = MutableLiveData("")
    var isLoadingCase = MutableLiveData(false)

    fun getTrack() : MutableLiveData<TrackResponse?> {
        return trackResponse
    }

    fun getPlayback() : MutableLiveData<PlaybackResponse> {
        return playback
    }

    fun onTrack() {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = spotifyRepo.getTrack()
            when (response) {
                is com.binargrouptwo.orpheum.constants.Result.Success -> {
                    trackResponse.postValue(response.value)
                }
                is com.binargrouptwo.orpheum.constants.Result.Error -> {
                    isFailurCaseMessage.postValue(response.toString())
                }
            }
        }
    }

    fun onPlay() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwble ->
            throwble.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            spotifyRepo.putPlay()
        }
    }
}