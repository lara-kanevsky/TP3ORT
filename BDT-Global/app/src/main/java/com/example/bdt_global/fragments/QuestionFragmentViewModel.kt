package com.example.bdt_global.fragments

import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bdt_global.entities.FormType
import com.example.bdt_global.entities.Option
import com.example.bdt_global.entities.QuestionScreen
import com.example.bdt_global.entities.StringOption
import com.example.bdt_global.entities.responseEntities.ResultsResponse
import com.example.bdt_global.services.FormServices
import kotlinx.coroutines.async

object QuestionFragmentViewModel : ViewModel() {

    private var questionScreenHistory = mutableListOf<QuestionScreen>()
    private var userAnswers = mutableListOf<Option>()
    private var currentScreenId = -1
    private var currentFormSharingId = -1
    private var currentFormPersonType = "error"
    private var currentFormLabel = "error"
    private var currentFormScreenCount = -1
    private var isCurrentQuestionFinal = false

    fun setCurrentFormSharingId(formSharingId: Int) {
        if (formSharingId > 0) {
            currentFormSharingId = formSharingId
        }
    }

    fun setCurrentFormType(formPersonType: String) {
        if (!formPersonType.isNullOrBlank()) {
            currentFormPersonType = formPersonType
        }
    }

    fun setCurrentFormLabel(formLabel: String) {
        if (!formLabel.isNullOrBlank()) {
            currentFormLabel = formLabel
        }
    }

    fun setCurrentFormScreenCount(formScreenCount: Int) {
        if (formScreenCount > 0) {
            currentFormScreenCount = formScreenCount + 1
        }
    }

    fun isCurrentQuestionFinal(): Boolean {
        return isCurrentQuestionFinal
    }

    fun getQuestionScreenHistorySize(): Int {
        return questionScreenHistory.size
    }

    fun getProgress(questionId: Int): Int {
        return questionId * 100 / currentFormScreenCount
    }

    fun saveQuestion(questionScreen: QuestionScreen) {
        questionScreenHistory.add(questionScreen)
    }

    fun saveAnswers(answers: List<Option>) {
        userAnswers.addAll(answers)
    }

    fun exitQuestionFragment(onBackPressedDispatcher: OnBackPressedDispatcher) {
        questionScreenHistory.clear()
        userAnswers.clear()
        onBackPressedDispatcher.onBackPressed()
    }

    suspend fun getFormTypes(): MutableList<FormType> {
        var formTypes = mutableListOf<FormType>()
        viewModelScope.async {
            formTypes.addAll(FormServices.getFormOptions())
        }.await()
        return formTypes
    }

    suspend fun getFirstQuestionScreen(): QuestionScreen {
        questionScreenHistory.clear()
        userAnswers.clear()

        lateinit var firstQuestionScreen: QuestionScreen
        viewModelScope.async {
            firstQuestionScreen = FormServices.getFirstQuestionScreen(currentFormPersonType)
            currentScreenId = firstQuestionScreen.screenId
            isCurrentQuestionFinal = firstQuestionScreen.isFinal
        }.await()
        return firstQuestionScreen
    }

    fun getPreviousQuestionScreen(): QuestionScreen {
        isCurrentQuestionFinal = false
        questionScreenHistory.removeLast()
        val questionScreenToLoad = questionScreenHistory.removeLast()
        if (questionScreenToLoad.answers.getAnswersCount() != 0) {
            userAnswers.removeAt(userAnswers.size - questionScreenToLoad.answers.getAnswersCount())
        }
        return questionScreenToLoad
    }

    suspend fun getNextQuestionScreen(currentQuestionScreenAnswers: List<Option>): QuestionScreen {
        lateinit var nextQuestionScreen: QuestionScreen
        var currentQuestionScreen = questionScreenHistory.last()
        currentQuestionScreen.completeAnswers(currentQuestionScreenAnswers)
        viewModelScope.async {
            nextQuestionScreen = FormServices.getNextQuestionScreen(
                currentFormPersonType,
                currentScreenId,
                currentQuestionScreen.answers.getAnswers()
            )
            currentScreenId = nextQuestionScreen.screenId
            isCurrentQuestionFinal = nextQuestionScreen.isFinal
        }.await()
        return nextQuestionScreen
    }

    suspend fun skipQuestion(): QuestionScreen {
        lateinit var nextQuestionScreen: QuestionScreen
        var currentQuestionScreen = questionScreenHistory.last()
        val skipOption = getSkipOption(currentQuestionScreen.answers.getOptions())
        viewModelScope.async {
            nextQuestionScreen =
                FormServices.getNextQuestionScreen(
                    currentFormPersonType,
                    currentScreenId,
                    skipOption
                )
            currentScreenId = nextQuestionScreen.screenId
            isCurrentQuestionFinal = nextQuestionScreen.isFinal
        }.await()
        return nextQuestionScreen
    }

    private fun getSkipOption(options: List<Option>): List<Option> {
        if (!options.isNullOrEmpty()) {
            return listOf(buildSkipOption(options[0]))
        } else {
            throw Error("There are no options")
        }
    }

    private fun buildSkipOption(option: Option): Option {
        return StringOption(
            option.type,
            option.optionId,
            "-1",
            option.value,
            option.nextScreenNavigationId,
            option.nextDefaultScreenNavigationId,
            option.screenId,
            option.measureUnit
        )
    }

    suspend fun getResults(): ResultsResponse {
        lateinit var myResults: ResultsResponse
        viewModelScope.async {
            myResults = FormServices.getResults(currentFormPersonType, userAnswers)
            UserViewModel.saveResults(myResults)
            questionScreenHistory.clear()
            userAnswers.clear()
        }.await()
        return myResults
    }

}
