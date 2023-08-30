package com.binargrouptwo.orpheum.ui.playmusic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepo
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepoManager
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModel

object PlayMusicViewModelFactory: ViewModelProvider.Factory {

    private val spotifyRepoManager = SpotifyRepoManager()
    private val spotifyRepo = SpotifyRepo(spotifyRepoManager)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpotifyViewModel(spotifyRepo) as T
    }

}