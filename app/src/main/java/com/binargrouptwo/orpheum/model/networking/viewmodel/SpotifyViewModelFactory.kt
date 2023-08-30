package com.binargrouptwo.orpheum.model.networking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepo
import com.binargrouptwo.orpheum.model.networking.repository.SpotifyRepoManager

object SpotifyViewModelFactory:ViewModelProvider.Factory{
    private val spotifyRepoManager= SpotifyRepoManager()
    private val spotifyRepo= SpotifyRepo(spotifyRepoManager)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpotifyViewModel(spotifyRepo) as T
    }
}