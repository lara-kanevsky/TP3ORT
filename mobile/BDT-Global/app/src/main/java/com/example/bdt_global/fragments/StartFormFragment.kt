package com.example.bdt_global.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.R
import com.example.bdt_global.databinding.FragmentStartFormBinding
import kotlinx.coroutines.launch

class StartFormFragment : Fragment() {

    private var _binding: FragmentStartFormBinding? = null
    private val binding get() = _binding!!
    private var myButtonFormType: Int = R.layout.button_form_type

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartFormBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            try {
                loadFormTypes()
            } catch (e: java.net.SocketTimeoutException) {
                Log.i("IBEAPP", "SocketTimeoutException: ${e.message}")
                throwErrorDialog(getString(R.string.connection_error))
            } catch (e: Error) {
                Log.i("IBEAPP", e.toString())
            } catch (t: Throwable) {
                Log.i("IBEAPP", t.toString())
            }
        }

        return binding.root
    }

    private suspend fun loadFormTypes() {
        val myFormOptions = binding.formOptions

        myFormOptions.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        var myFormTypes = QuestionFragmentViewModel.getFormTypes()

        for (formType in myFormTypes) {
            val myButton = inflater.inflate(
                myButtonFormType,
                myFormOptions,
                false
            ) as Button
            myButton.id = formType.id
            myButton.text = formType.label

            myButton.setOnClickListener {
                goToFirstQuestion(formType.label, formType.type)
            }

            myFormOptions.addView(myButton)
        }
    }

    private fun goToFirstQuestion(label: String, type: String) {
        QuestionFragmentViewModel.setCurrentFormType(type)
        //val action = StartFormFragmentDirections.actionStartFormFragmentToQuestionFragment(label)
        //findNavController().navigate(action)
    }

    private fun throwErrorDialog(text: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_error)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvError: TextView = dialog.findViewById(R.id.tvError)
        tvError.text = text

        val understoodButton: Button = dialog.findViewById(R.id.buttonUnderstood)
        understoodButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}
