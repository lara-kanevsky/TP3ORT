package com.example.bdt_global.entities

import com.example.bdt_global.entities.responseEntities.EditTextResponse
import com.example.bdt_global.entities.responseEntities.OptionResponse
import com.example.bdt_global.entities.responseEntities.QuestionScreenResponse

class QuestionScreen(
    var type: String,
    var screenId: Int,
    var navigationId: String,
    var formId: Int,
    var isFinal: Boolean,
    var category: Category,
    var question: String,
    var answers: AnswerOptions
) {
    fun toQuestionScreenResponse(): QuestionScreenResponse {
        lateinit var questionScreenResponse: QuestionScreenResponse
        try {
            questionScreenResponse =
                QuestionScreenResponse(
                    type,
                    screenId,
                    navigationId,
                    formId,
                    isFinal,
                    category,
                    question,
                    buildOptionResponseList(answers)
                )
        } catch (e: Error) {
            throw Error("Error obtaining the question screen", e)
        }
        return questionScreenResponse
    }

    private fun buildOptionResponseList(answers: AnswerOptions): List<OptionResponse> {
        var optionResponseList = mutableListOf<OptionResponse>()
        answers.getOptions().forEach {
            optionResponseList.add(buildOptionResponse(it))
        }
        return optionResponseList
    }

    private fun buildOptionResponse(option: Option): OptionResponse {
        lateinit var optionResponse: OptionResponse
        if (option.type == "RadioButton") {
            optionResponse = OptionResponse(
                option.type, option.optionId,
                option.label,
                option.value,
                option.nextScreenNavigationId,
                option.nextDefaultScreenNavigationId,
                option.screenId,
                option.measureUnit
            )
        } else if (option.type == "EditText") {
            optionResponse = EditTextResponse(
                option.type, option.optionId,
                option.label,
                option.value,
                option.nextScreenNavigationId,
                option.nextDefaultScreenNavigationId,
                option.screenId,
                option.measureUnit,
                (option as NumberInput).userInput.toFloat()
            )
        } else {
            throw Error("Error identifying the answer options")
        }
        return optionResponse
    }

    fun completeAnswers(userAnswers: List<Option>) {
        try {
            if (userAnswers.isNullOrEmpty()) {
                throw Error("The question has no answers")
            } else {
                answers.completeAnswers(userAnswers)
            }
        } catch (e: Error) {
            throw Error("Error completing the answer options", e)
        }
    }

}
