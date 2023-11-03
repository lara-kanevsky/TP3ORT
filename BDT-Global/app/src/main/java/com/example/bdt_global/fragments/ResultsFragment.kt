package com.example.bdt_global.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.databinding.FragmentResultsBinding
import kotlinx.coroutines.launch

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            val results = QuestionFragmentViewModel.getResults()
            binding.tvResultCO2.text = results.tonsCO2.toString()
            binding.tvCarobPerYearEquivalence.text = results.equivalences.carobPerYearEquivalence
            binding.tvKmByCarEquivalence.text = results.equivalences.kmByCarEquivalence
            binding.tvFlightsEquivalence.text = results.equivalences.flightsEquivalence
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.buttonReturnToStart.setOnClickListener {
            onClickButtonReturnToStart()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            onClickButtonReturnToStart()
        }
    }

    private fun onClickButtonReturnToStart() {
        val action = ResultsFragmentDirections.actionResultsFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }

}
