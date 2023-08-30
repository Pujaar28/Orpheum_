package com.binargrouptwo.orpheum.ui.onBoarding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.FragmentOnBoardingThreeBinding
import com.binargrouptwo.orpheum.ui.login.LoginActivity
import com.binargrouptwo.orpheum.utils.Preferences


class OnBoardingThree : Fragment() {
    lateinit var binding: FragmentOnBoardingThreeBinding
    lateinit var preferences: Preferences
    var videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingThreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPreferences()
        setVideo()
        setUpBtnStarted()
    }

    private fun setUpBtnStarted() {
        binding.btnGetStarted.setOnClickListener {
            val intentLogin =
                Intent(this@OnBoardingThree.requireContext(), LoginActivity::class.java)
            startActivity(intentLogin)
            preferences.setValues("ob1", "Login")
            activity?.finishAffinity()
        }
        binding.btnBack.setOnClickListener {
            val onBoardTwo = OnBoardingTwo()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                val onBoardThree =
                    fragmentManager.findFragmentByTag(OnBoardingThree::class.java.simpleName)
                onBoardThree?.let { hide(it) }
                replace(
                    R.id.frame_container,
                    onBoardTwo,
                    OnBoardingTwo::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }

    fun setVideo() {
        binding.apply {
            videoUrl = "android.resource://" + requireContext().packageName +"/"+ R.raw.raw_video
            val uri: Uri = Uri.parse(videoUrl)
            videoViewOffline.setVideoURI(uri)
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoViewOffline)
            mediaController.setMediaPlayer(videoViewOffline)
            videoViewOffline.start()
            videoViewOffline.setOnPreparedListener { it.isLooping = true }
        }
    }

    fun setPreferences() {
        preferences = Preferences(requireContext())
        if (preferences.getValues("ob1").equals("Login")) {
            activity?.finishAffinity()
            val intent = Intent(this@OnBoardingThree.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

    }

}