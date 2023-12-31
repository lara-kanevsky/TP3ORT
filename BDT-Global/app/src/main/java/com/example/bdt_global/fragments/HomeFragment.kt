package com.example.bdt_global.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonLogIn.setOnClickListener {
            goToLogIn()
        }

        binding.buttonRegister.setOnClickListener {
            goToRegister()
        }

        return binding.root
    }

    private fun goToLogIn() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToLogInFragment()
        findNavController().navigate(action)
    }

    private fun goToRegister() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

}
