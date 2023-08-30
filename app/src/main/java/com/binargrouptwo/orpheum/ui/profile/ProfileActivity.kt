package com.binargrouptwo.orpheum.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binargrouptwo.orpheum.adapter.TrackAdapter
import com.binargrouptwo.orpheum.databinding.ActivityProfileBinding
import com.binargrouptwo.orpheum.model.entities.profile.tracks.TrackResponse
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModel
import com.binargrouptwo.orpheum.model.networking.viewmodel.SpotifyViewModelFactory
import com.binargrouptwo.orpheum.ui.home.MainActivity
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    lateinit var trackAdapter: TrackAdapter
    private val viewModelSpotify by viewModels<SpotifyViewModel> {
        SpotifyViewModelFactory
    }
    private val viewModel by viewModels<SpotifyViewModel>{
        SpotifyViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservable()
        initAdapter()
        setUpAction()

        viewModel.onLoadProfile()
        viewModel.onUserTrack()
    }

    private fun setUpAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.ivPlayAndPause.setOnClickListener {
            viewModelSpotify.onPlayerPlay()
            viewModelSpotify.onPlayerPause()
        }
    }

    private fun initAdapter() {
        trackAdapter = TrackAdapter()
        trackAdapter.setData(arrayListOf())
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvProfile.adapter = trackAdapter
            rvProfile.layoutManager = linearLayout
        }
    }

    private fun initObservable() {
        viewModel.getProfile().observe(this) {
            binding.tvName.text = it?.displayName
            Glide.with(binding.root)
                .load(it?.images?.get(0)?.url)
                .into(binding.ivProfile)
        }

        viewModel.getTrack().observe(this) {
            it?.let { it1 -> onSuccess(it1) }
        }
    }

    private fun onSuccess(trackResponse : TrackResponse) {
        val listTrack = trackResponse.items
        listTrack.let { listTrack -> trackAdapter.setData(listTrack) }
        trackAdapter.notifyDataSetChanged()
    }
}