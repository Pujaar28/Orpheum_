package com.binargrouptwo.orpheum.ui.home.online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binargrouptwo.orpheum.databinding.ActivitySpotifyTestingBinding

class SpotifyActivityTesting : AppCompatActivity() {
    private lateinit var binding: ActivitySpotifyTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpotifyTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData(){
        val spotifyId = intent.getStringExtra("spotify_id")
        val spotifyUsername = intent.getStringExtra("spotify_display_name")
        val spotifyEmail = intent.getStringExtra("spotify_email")
        val spotifyAvatarURL = intent.getStringExtra("spotify_avatar")
        val spotifyAccessToken = intent.getStringExtra("spotify_access_token")

        binding.apply {
            tvSpotifyId.text = spotifyId
            tvSpotifyUsername.text = spotifyUsername
            tvSpotifyEmail.text = spotifyEmail
            tvSpotifyAvatarUrl.text = spotifyAvatarURL
            tvSpotifyAccessToken.text = spotifyAccessToken
        }
    }
}