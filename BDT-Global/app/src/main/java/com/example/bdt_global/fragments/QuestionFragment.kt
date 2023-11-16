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
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bdt_global.R
import com.example.bdt_global.databinding.FragmentQuestionBinding
import com.example.bdt_global.entities.AnswerOptions
import com.example.bdt_global.entities.NumberInput
import com.example.bdt_global.entities.NumberInputs
import com.example.bdt_global.entities.QuestionScreen
import com.example.bdt_global.entities.StringOption
import com.example.bdt_global.entities.StringOptions
import com.example.bdt_global.entities.viewEntities.AnswerBox
import com.example.bdt_global.entities.viewEntities.EditTextAnswerBox
import com.example.bdt_global.entities.viewEntities.RadioButtonAnswerBox
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private lateinit var myAnswerBoxContainer: LinearLayout
    private var myRadioButtonAnswerBox: Int = R.layout.answer_box_radio_button
    private var myEditTextAnswerBox: Int = R.layout.answer_box_edit_text
    private var myRadioButtonItem: Int = R.layout.item_radio_button
    private var myEditTextItem: Int = R.layout.item_edit_text

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        myAnswerBoxContainer = binding.myAnswerBoxContainer

        binding.tvFormType.text = QuestionFragmentArgs.fromBundle(requireArguments()).formType

        lifecycleScope.launch {
            try {
                loadQuestionScreen(QuestionFragmentViewModel.getFirstQuestionScreen())
            } catch (e: java.net.SocketTimeoutException) {
                Log.i("TP3ORT", "SocketTimeoutException: ${e.message}")
                throwErrorDialog(getString(R.string.error_connection))
            } catch (e: Error) {
                Log.i("TP3ORT", e.toString())
            } catch (t: Throwable) {
                Log.i("TP3ORT", t.toString())
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureConfirmExitDialog()
    }

    override fun onStart() {
        super.onStart()

        binding.buttonPrevious.setOnClickListener {
            onClickButtonPrevious()
        }

        binding.buttonNext.setOnClickListener {
            onClickButtonNext()
        }

        binding.tvSkipQuestion.setOnClickListener {
            onClickSkipQuestion()
        }
    }

    private fun onClickSkipQuestion() {
        try {
            when (QuestionFragmentViewModel.isCurrentQuestionFinal()) {
                true -> {
                    goToResults()
                }

                false -> {
                    skipQuestion()
                }
            }
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun onClickButtonPrevious() {
        if (QuestionFragmentViewModel.getQuestionScreenHistorySize() == 1) {
            QuestionFragmentViewModel.exitQuestionFragment(requireActivity().onBackPressedDispatcher)
        } else {
            loadPreviousQuestionScreen()
        }
    }

    private fun onClickButtonNext() {
        val myAnswerBox = myAnswerBoxContainer.getChildAt(0) as AnswerBox

        try {
            myAnswerBox.checkCompleted()
            QuestionFragmentViewModel.saveAnswers(myAnswerBox.getAnswers())
            when (QuestionFragmentViewModel.isCurrentQuestionFinal()) {
                true -> {
                    goToResults()
                }

                false -> {
                    goToNextQuestionScreen()
                }
            }
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun goToResults() {
        val action =
            QuestionFragmentDirections.actionQuestionFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    private fun goToNextQuestionScreen() {
        val myAnswerBox = myAnswerBoxContainer.getChildAt(0) as AnswerBox

        try {
            val answers = myAnswerBox.getAnswers()
            lifecycleScope.launch {
                try {
                    loadQuestionScreen(QuestionFragmentViewModel.getNextQuestionScreen(answers))
                } catch (e: java.net.SocketTimeoutException) {
                    Log.i("TP3ORT", "SocketTimeoutException: ${e.message}")
                    throwErrorDialog(getString(R.string.error_connection))
                } catch (e: Error) {
                    Log.i("TP3ORT", e.toString())
                } catch (t: Throwable) {
                    Log.i("TP3ORT", t.toString())
                }
            }
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun skipQuestion() {
        try {
            lifecycleScope.launch {
                try {
                    loadQuestionScreen(QuestionFragmentViewModel.skipQuestion())
                } catch (e: java.net.SocketTimeoutException) {
                    Log.i("TP3ORT", "SocketTimeoutException: ${e.message}")
                    throwErrorDialog(getString(R.string.error_connection))
                } catch (e: Error) {
                    Log.i("TP3ORT", e.toString())
                } catch (t: Throwable) {
                    Log.i("TP3ORT", t.toString())
                }
            }
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun setButtonNextText() {
        when (QuestionFragmentViewModel.isCurrentQuestionFinal()) {
            true -> {
                binding.buttonNext.text = getString(R.string.question_fragment_button_finish)
            }

            false -> {
                binding.buttonNext.text = getString(R.string.question_fragment_button_next)
            }
        }
    }

    private fun loadQuestionScreen(questionScreen: QuestionScreen) {
        QuestionFragmentViewModel.saveQuestion(questionScreen)
        loadProgressBar(QuestionFragmentViewModel.getProgress(questionScreen.screenId))
        loadQuestion(questionScreen.question)
        loadAnswerBox(questionScreen.answers)
        setButtonNextText()
    }

    private fun loadProgressBar(completePercentage: Int) {
        binding.progressBar.setProgress(completePercentage)
    }

    private fun loadQuestion(question: String) {
        binding.tvQuestion.text = question
    }

    private fun loadAnswerBox(answers: AnswerOptions) {
        myAnswerBoxContainer.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        when (answers) {
            is StringOptions -> loadRadioButtons(answers, inflater)
            is NumberInputs -> loadEditTexts(answers, inflater)
            else -> throw Error()
        }
    }

    private fun loadRadioButtons(options: StringOptions, inflater: LayoutInflater) {
        val myAnswerBox =
            inflater.inflate(
                myRadioButtonAnswerBox,
                myAnswerBoxContainer,
                false
            ) as RadioButtonAnswerBox

        for (option in options.getOptions()) {
            val radioButton =
                inflater.inflate(myRadioButtonItem, myAnswerBox, false) as RadioButton
            radioButton.text = option.label
            radioButton.id = option.optionId
            radioButton.isChecked = (option as StringOption).isSelected

            val tagValues = HashMap<String, Any>()
            tagValues[getString(R.string.view_tag_key_type)] = option.type
            tagValues[getString(R.string.view_tag_key_value)] = option.value
            tagValues[getString(R.string.view_tag_key_next_screen_navigation_id)] =
                option.nextScreenNavigationId
            tagValues[getString(R.string.view_tag_key_next_default_screen_navigation_id)] =
                option.nextDefaultScreenNavigationId
            tagValues[getString(R.string.view_tag_key_screen_id)] = option.screenId
            tagValues[getString(R.string.view_tag_key_measure_unit)] = option.measureUnit
            radioButton.tag = tagValues

            myAnswerBox.addView(radioButton)
        }

        this.myAnswerBoxContainer.addView(myAnswerBox)
    }

    private fun loadEditTexts(options: NumberInputs, inflater: LayoutInflater) {
        val myAnswerBox =
            inflater.inflate(
                myEditTextAnswerBox,
                myAnswerBoxContainer,
                false
            ) as EditTextAnswerBox

        for (option in options.getOptions()) {
            val editText = inflater.inflate(myEditTextItem, myAnswerBox, false) as EditText
            editText.hint = option.label
            editText.id = option.optionId
            editText.setText((option as NumberInput).userInput)

            val tagValues = HashMap<String, Any>()
            tagValues[getString(R.string.view_tag_key_type)] = option.type
            tagValues[getString(R.string.view_tag_key_value)] = option.value
            tagValues[getString(R.string.view_tag_key_next_screen_navigation_id)] =
                option.nextScreenNavigationId
            tagValues[getString(R.string.view_tag_key_next_default_screen_navigation_id)] =
                option.nextDefaultScreenNavigationId
            tagValues[getString(R.string.view_tag_key_screen_id)] = option.screenId
            tagValues[getString(R.string.view_tag_key_measure_unit)] = option.measureUnit
            editText.tag = tagValues

            myAnswerBox.addView(editText)
        }

        myAnswerBoxContainer.addView(myAnswerBox)
    }

    private fun loadPreviousQuestionScreen() {
        loadQuestionScreen(QuestionFragmentViewModel.getPreviousQuestionScreen())
    }

    private fun throwSnackbar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
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

    private fun configureConfirmExitDialog() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_confirm)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val question: TextView = dialog.findViewById(R.id.tvConfirmQuestion)
            question.text = getString(R.string.question_fragment_confirm_exit)

            val yesButton: Button = dialog.findViewById(R.id.buttonYes)
            val noButton: Button = dialog.findViewById(R.id.buttonNo)

            yesButton.setOnClickListener {
                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    dialog.dismiss()
                }
            }
            noButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

}
