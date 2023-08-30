package com.binargrouptwo.orpheum.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.binargrouptwo.orpheum.ui.onBoarding.OnBoardingActivity
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val MAX_STREAMS = 1
    private var soundId = 0
    private lateinit var soundPool: SoundPool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPlay()
    }

    private fun setPlay() {
        val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        val builder = SoundPool.Builder()
        builder.setAudioAttributes(audioAttributes).setMaxStreams(MAX_STREAMS)
        this.soundPool = builder.build()
        soundPool.setOnLoadCompleteListener { _, _, _ ->
                playBackSound()
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, OnBoardingActivity::class.java)).also {
                        finishSplash()
                    }
                }, 1000)
            }
            soundId = soundPool.load(this, R.raw.res_ashiap, 1)
    }

    private fun playBackSound() {
        val managerSystem = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val actualVolume = managerSystem.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolume = managerSystem.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val volume = actualVolume / maxVolume
        this.soundPool.play(this.soundId, volume, volume, 1, 0, 1f)
    }

    private fun finishSplash() {
        finish()
    }
}