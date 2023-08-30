package com.binargrouptwo.orpheum.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.FragmentOnBoardingOneBinding
import com.binargrouptwo.orpheum.ui.login.LoginActivity
import com.binargrouptwo.orpheum.utils.Preferences


class OnBoardingOne : Fragment() {
    lateinit var binding: FragmentOnBoardingOneBinding
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPreferences()
        onAction()
    }

    private fun onAction() {
        binding.apply {
            btnNextOne.setOnClickListener {
                val onBoardTwo = OnBoardingTwo()
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    val onBoardOne =
                        fragmentManager.findFragmentByTag(OnBoardingOne::class.java.simpleName)
                    onBoardOne?.let { hide(it) }
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
    }

    fun setPreferences() {
        preferences = Preferences(requireContext())
        if (preferences.getValues("ob1").equals("Login")) {
            activity?.finishAffinity()
            val intent = Intent(this@OnBoardingOne.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}