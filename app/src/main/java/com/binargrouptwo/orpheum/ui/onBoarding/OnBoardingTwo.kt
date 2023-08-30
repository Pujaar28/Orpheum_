package com.binargrouptwo.orpheum.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binargrouptwo.orpheum.R
import com.binargrouptwo.orpheum.databinding.FragmentOnBoardingTwoBinding


class OnBoardingTwo : Fragment() {
    lateinit var binding: FragmentOnBoardingTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOnBoardingTwoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     onAction()
    }
    private fun onAction(){
        binding.apply {
            btnNextTwo.setOnClickListener {
                val onBoardThree = OnBoardingThree()
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    val onBoardTwo = fragmentManager.findFragmentByTag(OnBoardingTwo::class.java.simpleName)
                    onBoardTwo?.let { hide(it) }
                    replace(
                        R.id.frame_container,
                        onBoardThree,
                        OnBoardingThree::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }
            btnBackOne.setOnClickListener {
                val onBoardOne = OnBoardingOne()
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    val onBoardTwo = fragmentManager.findFragmentByTag(OnBoardingTwo::class.java.simpleName)
                    onBoardTwo?.let { hide(it) }
                    replace(
                        R.id.frame_container,
                        onBoardOne,
                        OnBoardingOne::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}