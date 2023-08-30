package com.binargrouptwo.orpheum.ui.home.spotifyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binargrouptwo.orpheum.model.entities.library.adapter.PlaylistAdapter
import com.binargrouptwo.orpheum.databinding.ActivitySpotifyBinding
import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.binargrouptwo.orpheum.model.entities.browse.adapter.FeaturedPlaylistAdapter
import com.binargrouptwo.orpheum.model.entities.browse.adapter.NewReleaseAdapter
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModel
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModelFactory

class SpotifyActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpotifyBinding
    lateinit var adapterSpotify: PlaylistAdapter

    //Browse
    lateinit var adapterNewRelease: NewReleaseAdapter
    lateinit var adapterFeaturedPlaylist: FeaturedPlaylistAdapter
    //Library
    lateinit var adapterUserPlaylist: PlaylistAdapter

    private val viewModel by viewModels<SpotifyViewModel> { SpotifyViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpotifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Observable+Adapter+ViewModel Initialization
        /*Observable:*/initNewReleaseObservable()
        /*Adapter:*/initNewReleaseAdapter()
        /*ViewModel:*/viewModel.onNewRelease()
        //Button
        buttonTest()
    }

    private fun buttonTest(){
        binding.apply {
            btnSpotify.setOnClickListener { viewModel.onPlayerPause() }
        }
    }

    /*------------------------ADAPTER--------------------------------*/
    private fun initAdapter() {
        adapterSpotify = PlaylistAdapter()
        adapterSpotify.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        /*binding.rvUserGithub.apply {
            layoutManager = linearLayout
            adapter = adapterSpotify
        }*/
        binding.rvUserGithub.adapter = adapterSpotify
        binding.rvUserGithub.layoutManager = linearLayout
    }
    //Browse
    private fun initFeaturedPlaylistAdapter() {
        adapterFeaturedPlaylist = FeaturedPlaylistAdapter()
        adapterFeaturedPlaylist.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvUserGithub.adapter = adapterFeaturedPlaylist
        binding.rvUserGithub.layoutManager = linearLayout
    }
    private fun initNewReleaseAdapter() {
        adapterNewRelease = NewReleaseAdapter()
        adapterNewRelease.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvUserGithub.adapter = adapterNewRelease
        binding.rvUserGithub.layoutManager = linearLayout
    }
    //Library
    private fun initUserPlaylistAdapter() {
        adapterUserPlaylist= PlaylistAdapter()
        adapterUserPlaylist.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvUserGithub.adapter = adapterUserPlaylist
        binding.rvUserGithub.layoutManager = linearLayout
    }
    /*------------------------OBSERVABLE--------------------------------*/
    private fun initObservable() {
        /*viewModel.getNewRelease().observe(this) {
            onSuccessLoad(it)
        }*/
        viewModel.getUserPlaylist().observe(this){
            onSuccessLoad(it)
        }
    }
    //Browse
    private fun initFeaturedPlaylistObservable() {
        viewModel.getFeaturedPlaylist().observe(this){
            onFeaturedPlaylist(it)
        }
    }
    private fun initNewReleaseObservable() {
        viewModel.getNewRelease().observe(this){
            onNewRelease(it)
        }
    }
    //Library
    private fun initUserPlaylistObservable() {
        viewModel.getUserPlaylist().observe(this){
            onUserPlaylist(it)
        }
    }
    /*------------------------ON SUCCESS--------------------------------*/
    private fun onSuccessLoad(featuredPlaylistResponse: PlaylistResponse) {
        val listData = featuredPlaylistResponse.items
        listData?.let { adapterSpotify.setData(it) }
        adapterSpotify.notifyDataSetChanged()
    }
    //Browse
    private fun onFeaturedPlaylist(featuredPlaylistResponse: FeaturedPlaylistResponse) {
        val listData = featuredPlaylistResponse.playlists?.items
        listData?.let { adapterFeaturedPlaylist.setData(it) }
        adapterFeaturedPlaylist.notifyDataSetChanged()
    }
    private fun onNewRelease(newReleaseResponse: NewReleaseResponse) {
        val listData = newReleaseResponse.albums?.items
        listData?.let { adapterNewRelease.setData(it) }
        adapterNewRelease.notifyDataSetChanged()
    }
    //Library
    private fun onUserPlaylist(PlaylistResponse: PlaylistResponse) {
        val listData = PlaylistResponse.items
        listData?.let { adapterUserPlaylist.setData(it) }
        adapterUserPlaylist.notifyDataSetChanged()
    }

}