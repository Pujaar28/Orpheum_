package com.binargrouptwo.orpheum.ui.home.online

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binargrouptwo.orpheum.databinding.FragmentOnlineBinding
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedPlaylistResponse
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.binargrouptwo.orpheum.model.entities.browse.adapter.FeaturedPlaylistAdapter
import com.binargrouptwo.orpheum.model.entities.browse.adapter.NewReleaseAdapter
import com.binargrouptwo.orpheum.model.entities.library.PlaylistResponse
import com.binargrouptwo.orpheum.model.entities.library.adapter.PlaylistAdapter
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModel
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModelFactory

class OnlineFragment : Fragment() {
    lateinit var binding: FragmentOnlineBinding
    //Browse
    lateinit var adapterNewRelease: NewReleaseAdapter
    lateinit var adapterFeaturedPlaylist: FeaturedPlaylistAdapter
    //Library
    lateinit var adapterUserPlaylist: PlaylistAdapter

    private val viewModel by viewModels<SpotifyViewModel> { SpotifyViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnlineBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()
    }

    private fun initRecView(){
        /*Observable:*/initNewReleaseObservable()
        initFeaturedPlaylistObservable()
        initUserPlaylistObservable()
        /*Adapter:*/initNewReleaseAdapter()
        initFeaturedPlaylistAdapter()
        initUserPlaylistAdapter()
        /*ViewModel:*/viewModel.onNewRelease()
        viewModel.onFeaturedPlaylist()
        viewModel.onUserPlaylist()

    }

    /*------------ADAPTER-------------*/
    //Browse
    private fun initFeaturedPlaylistAdapter() {
        adapterFeaturedPlaylist = FeaturedPlaylistAdapter()
        adapterFeaturedPlaylist.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvPlaylist.adapter = adapterFeaturedPlaylist
        binding.rvPlaylist.layoutManager = linearLayout
    }
    private fun initNewReleaseAdapter() {
        adapterNewRelease = NewReleaseAdapter()
        adapterNewRelease.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvNewRelease.adapter = adapterNewRelease
        binding.rvNewRelease.layoutManager = linearLayout
    }
    //Library
    private fun initUserPlaylistAdapter() {
        adapterUserPlaylist= PlaylistAdapter()
        adapterUserPlaylist.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    /*------------OBSERVABLE-------------*/
    //Browse
    private fun initFeaturedPlaylistObservable() {
        viewModel.getFeaturedPlaylist().observe(viewLifecycleOwner){
            onFeaturedPlaylist(it)
        }
    }
    private fun initNewReleaseObservable() {
        viewModel.getNewRelease().observe(viewLifecycleOwner){
            onNewRelease(it)
        }
    }
    //Library
    private fun initUserPlaylistObservable() {
        viewModel.getUserPlaylist().observe(viewLifecycleOwner){
            onUserPlaylist(it)
        }
    }
    /*------------ON SUCCESS-------------*/
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