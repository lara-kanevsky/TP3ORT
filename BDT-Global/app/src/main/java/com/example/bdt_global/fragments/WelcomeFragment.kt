package com.example.bdt_global.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.R
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

        binding.buttonMyData.setOnClickListener {
            goToMyData()
        }

        binding.buttonMoreInfo.setOnClickListener {
            goToFormInfo()
        }

        binding.buttonLogOut.setOnClickListener {
            throwConfirmLogOutDialog()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            binding.buttonLogOut.callOnClick()
        }
    }

    private fun goToStartForm() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToStartFormFragment()
        findNavController().navigate(action)
    }

    private fun goToMyData() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToMyDataFragment()
        findNavController().navigate(action)
    }

    private fun goToFormInfo() {
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToFormInfoFragment()
        findNavController().navigate(action)
    }

    private fun logOut() {
        UserViewModel.logOut()
        val action =
            WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun throwConfirmLogOutDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_confirm)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val question: TextView = dialog.findViewById(R.id.tvConfirmQuestion)
        question.text = getString(R.string.welcome_fragment_confirm_log_out)

        val yesButton: Button = dialog.findViewById(R.id.buttonYes)
        val noButton: Button = dialog.findViewById(R.id.buttonNo)

        yesButton.setOnClickListener {
            logOut()
            dialog.dismiss()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}
