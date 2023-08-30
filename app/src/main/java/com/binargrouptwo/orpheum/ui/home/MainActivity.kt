package com.binargrouptwo.orpheum.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binargrouptwo.orpheum.adapter.MainAdapter
import com.binargrouptwo.orpheum.databinding.ActivityMainBinding
import com.binargrouptwo.orpheum.utils.Preferences
import com.binargrouptwo.orpheum.ui.profile.ProfileActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var tabAdapter: MainAdapter
    private val tabTitle = listOf(
        "Online",
        "My Music"
    )
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPreferences()
        setUpViewPager()
        setUpBtn()
    }

    private fun setUpBtn() {
        binding.ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpViewPager() {
        val title = ArrayList(tabTitle)
        binding.apply {
            vpHome.apply {
                tabAdapter = MainAdapter(this@MainActivity)
                adapter = tabAdapter
                currentItem = 0
            }
            TabLayoutMediator(tlHome, vpHome) {tab, position ->
                tab.text = title[position]
            }.attach()
        }
    }

    private fun setPreferences() {
        preferences = Preferences(this)
        preferences.setValues("login", "home")
    }
}