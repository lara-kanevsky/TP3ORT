package com.example.bdt_global.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.databinding.FragmentLogInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)

        binding.buttonLogIn.setOnClickListener {
            logIn()
        }

        return binding.root
    }

    private fun logIn() {
        var email = binding.etUserMail.text.toString()
        var password = binding.etUserPassword.text.toString()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Snackbar.make(
                binding.root,
                "Ingrese credenciales válidas",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            LogInViewModel.logIn(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        goToWelcome()
                    } else {
                        Log.i("TP3ORT", task.exception?.message.toString())
                        Snackbar.make(
                            binding.root,
                            "Falló el inicio de sesión",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun goToWelcome() {
        val action =
            LogInFragmentDirections.actionLogInFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }

    override fun onStart() {
        super.onStart()
//        var currentUser = auth.getCurrentUser()
//        Log.i("TP3ORT", currentUser?.email.toString())
    }

}
