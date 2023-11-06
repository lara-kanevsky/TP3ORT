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

        loadData()

        binding.buttonReturn.setOnClickListener {
            returnToHome()
        }

        return binding.root
    }

    private fun returnToHome() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun loadData() {
        UserViewModel.getCurrentUser { user ->
            if (user != null) {
                binding.tvUserName.text = user.name
                binding.tvUserSurname.text = user.surname
                binding.tvUserDni.text = user.dni.toString()
                binding.tvUserMail.text = user.mail
                binding.tvUserAnswersCount.text = user.getResults().size.toString()
            }
        }
    }

}
