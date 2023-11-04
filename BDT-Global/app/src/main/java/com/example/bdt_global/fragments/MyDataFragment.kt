package com.example.bdt_global.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bdt_global.databinding.FragmentMyDataBinding

class MyDataFragment : Fragment() {
    private var _binding: FragmentMyDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyDataBinding.inflate(inflater, container, false)

        binding.buttonReturn.setOnClickListener {
            returnToHome()
        }

        return binding.root
    }

    private fun returnToHome() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

}
