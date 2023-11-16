package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName
import com.example.bdt_global.entities.AnswerOptions
import com.example.bdt_global.entities.Category
import com.example.bdt_global.entities.NumberInput
import com.example.bdt_global.entities.NumberInputs
import com.example.bdt_global.entities.QuestionScreen
import com.example.bdt_global.entities.StringOption
import com.example.bdt_global.entities.StringOptions

class QuestionScreenResponse(
    @SerializedName("type") var type: String,
    @SerializedName("screenId") var screenId: Int,
    @SerializedName("navigationId") var navigationId: String,
    @SerializedName("formId") var formId: Int,
    @SerializedName("isFinal") var isFinal: Boolean,
    @SerializedName("category") var category: Category,
    @SerializedName("question") var question: String,
    @SerializedName("options") var options: List<OptionResponse>
) {
    fun toQuestionScreen(): QuestionScreen {
        lateinit var questionScreen: QuestionScreen
        try {
            questionScreen =
                QuestionScreen(
                    type,
                    screenId,
                    navigationId,
                    formId,
                    isFinal,
                    category,
                    question,
                    buildAnswerOptions(options)
                )
        } catch (e: Error) {
            throw Error("Error obtaining the question screen", e)
        }
        return questionScreen
    }

    private fun buildAnswerOptions(optionsResponseList: List<OptionResponse>): AnswerOptions {
        lateinit var answerOptions: AnswerOptions
        try {
            if (optionsResponseList.isNullOrEmpty()) {
                throw Error("The question has no answers")
            } else if (optionsResponseList[0].type == "RadioButton") {
                answerOptions = StringOptions()
                optionsResponseList.forEach {
                    (answerOptions as StringOptions).addOption(
                        StringOption(
                            it.type,
                            it.optionId,
                            it.label,
                            it.value,
                            it.nextScreenNavigationId,
                            it.nextDefaultScreenNavigationId,
                            it.screenId,
                            it.measureUnit
                        )
                    )
                }
            } else if (optionsResponseList[0].type == "EditText") {
                answerOptions = NumberInputs()
                optionsResponseList.forEach {
                    (answerOptions as NumberInputs).addOption(
                        NumberInput(
                            it.type,
                            it.optionId,
                            it.label,
                            it.value,
                            it.nextScreenNavigationId,
                            it.nextDefaultScreenNavigationId,
                            it.screenId,
                            it.measureUnit
                        )
                    )
                }
            } else {
                throw Error("Error identifying the answer options")
            }
        } catch (e: Error) {
            throw Error("Error obtaining the answer options", e)
        }
        return answerOptions
    }

}
