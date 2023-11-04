package com.example.bdt_global.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.buttonRegister.setOnClickListener {
            register()
        }

        return binding.root
    }

    private fun register() {
        var email = binding.etUserMail.text.toString()
        var password = binding.etUserPassword.text.toString()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Snackbar.make(
                binding.root,
                "Ingrese credenciales válidas",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            LogInViewModel.register(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        goToLogIn()
                    } else {
                        Log.i("TP3ORT", task.exception?.message.toString())
                        Snackbar.make(
                            binding.root,
                            "Falló el registro",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun goToLogIn() {
        val action =
            RegisterFragmentDirections.actionRegisterFragmentToLogInFragment()
        findNavController().navigate(action)
    }

}
