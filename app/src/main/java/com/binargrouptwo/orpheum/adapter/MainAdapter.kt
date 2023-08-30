package com.binargrouptwo.orpheum.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.binargrouptwo.orpheum.ui.home.MainActivity
import com.binargrouptwo.orpheum.ui.home.mymusic.MyMusicFragment
import com.binargrouptwo.orpheum.ui.home.online.OnlineFragment

class MainAdapter(activity: MainActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnlineFragment()
            else -> MyMusicFragment()
        }
    }
}