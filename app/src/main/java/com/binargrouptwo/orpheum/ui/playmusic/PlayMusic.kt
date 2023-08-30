package com.binargrouptwo.orpheum.ui.playmusic

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.ActivityPlayMusicBinding
import com.bumptech.glide.Glide

class PlayMusic : AppCompatActivity() {

    private lateinit var binding: ActivityPlayMusicBinding
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_ARTIST = "artist"
        const val EXTRA_IMG = "img"
        const val EXTRA_MUSIC = "music"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val title = intent.getStringExtra(EXTRA_TITLE)
            val artist = intent.getStringExtra(EXTRA_ARTIST)
            val img = intent.getStringExtra(EXTRA_IMG)

            tvTitle.text = title
            tvArtist.text = artist
            Glide.with(this@PlayMusic)
                .load(img)
                .into(ivMusic)
        }

        binding.btnBack.setOnClickListener { finish() }

        binding.btnPlay.setOnClickListener {
//            mediaPlayer = MediaPlayer()
//            if (mediaPlayer.isPlaying == true) {
                onPlay()
//            } else {
//                onStopMusic()
//            }
        }
        binding.btnNext.setOnClickListener {
            onStopMusic()
        }
    }

    private fun onPlay() {
        val music = intent.getStringExtra(EXTRA_MUSIC)
        val audioUrl = music
        val mediaPlayer = MediaPlayer()

        try {
            binding.btnPlay.setImageDrawable(getDrawable(R.drawable.stop))
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(this, "musik on", Toast.LENGTH_SHORT).show()
    }

    private fun onStopMusic() {
        mediaPlayer = MediaPlayer()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
            binding.btnPlay.setImageDrawable(getDrawable(R.drawable.pause))
            Toast.makeText(this, "musik off", Toast.LENGTH_SHORT).show()
        }
    }
}