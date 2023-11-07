package com.example.bdt_global.entities

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
