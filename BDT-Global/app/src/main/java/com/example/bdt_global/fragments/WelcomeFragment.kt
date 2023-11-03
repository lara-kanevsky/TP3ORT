package com.example.bdt_global.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.buttonStart.setOnClickListener {
            goToStartForm()
        }

        binding.buttonMoreInfo.setOnClickListener {
            goToFormInfo()
        }

        binding.buttonLogOut.setOnClickListener {
            logOut()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        requireActivity().onBackPressedDispatcher.addCallback(this){
            logOut()
        }
    }

    private fun goToStartForm() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToStartFormFragment()
        findNavController().navigate(action)
    }

    private fun goToFormInfo() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToFormInfoFragment()
        findNavController().navigate(action)
    }

    private fun logOut() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
        findNavController().navigate(action)
    }

}
