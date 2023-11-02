package com.example.bdt_global.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.R
import com.example.bdt_global.databinding.FragmentLoginFormBinding
import com.example.bdt_global.databinding.FragmentStartFormBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginFormFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentLoginFormBinding? = null
    private val binding get() = _binding!!
    private var myButtonFormType: Int = R.layout.button_form_type

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginFormBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.LogInButton.setOnClickListener {
triggerSignIn()
        }

        return binding.root
    }

    public fun triggerSignIn(){
var email = binding.usernameInput.text.toString()
var password = binding.passwordInput.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    Log.i("IBEAPP", "signInWithEmail:success")
                    val user = auth.currentUser
                    var lare = task.result.user?.email !!
                    Log.i("IBEAPP", lare)
                    Log.i("IBEAPP", user.toString())
                    val action =
                        LoginFormFragmentDirections.actionLoginFormFragmentToHomeFragment()
                    findNavController().navigate(action)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Log.i("IBEAPP", "signInWithEmail:failure", task.exception)
                    Snackbar.make(binding.root, "Fallo el login.", Snackbar.LENGTH_SHORT).show()
                    //Toast.makeText(
                    //    baseContext,
                    //    "Authentication failed.",
                    //    Toast.LENGTH_SHORT,
                    //).show()
                    //updateUI(null)
                }
            }
        //updateUI(currentUser);
    }

    override fun onStart() {
        super.onStart()
        var currentUser = auth.getCurrentUser()

    }



}