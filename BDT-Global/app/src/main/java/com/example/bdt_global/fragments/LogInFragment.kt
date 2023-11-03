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

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.buttonLogIn.setOnClickListener {
            triggerSignIn()
        }

        return binding.root
    }

    private fun triggerSignIn() {
        var email = binding.etUserMail.text.toString()
        var password = binding.etUserPassword.text.toString()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Snackbar.make(binding.root, "Ingrese credenciales válidas", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithEmail:success")
                        Log.i("TP3ORT", "signInWithEmail:success")
                        val user = auth.currentUser
                        var userMail = task.result.user?.email!!
                        Log.i("TP3ORT", userMail)
                        Log.i("TP3ORT", user.toString())
                        val action =
                            LogInFragmentDirections.actionLogInFragmentToWelcomeFragment()
                        findNavController().navigate(action)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Log.i("TP3ORT", "signInWithEmail:failure", task.exception)
                        Snackbar.make(binding.root, "Falló el log in.", Snackbar.LENGTH_SHORT)
                            .show()
                        //updateUI(null)
                    }
                }
        }
        //updateUI(currentUser);
    }

    override fun onStart() {
        super.onStart()
        var currentUser = auth.getCurrentUser()
        Log.i("TP3ORT", currentUser?.email.toString())
    }

}
