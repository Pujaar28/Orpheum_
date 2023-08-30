package com.binargrouptwo.orpheum.ui.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val oneFragment = OnBoardingOne()
        val fragment = fragmentManager.findFragmentByTag(OnBoardingOne::class.java.simpleName)
        if (fragment !is OnBoardingOne) {
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, oneFragment, OnBoardingOne::class.simpleName)
                .commit()
        }
    }
}