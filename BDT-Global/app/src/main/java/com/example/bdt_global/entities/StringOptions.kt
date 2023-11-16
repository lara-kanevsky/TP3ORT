package com.example.bdt_global.entities

class StringOptions() : AnswerOptions() {

    private var strings = mutableListOf<StringOption>()
    private var answersCount: Int = 0

    override fun addOption(option: Option) {
        if (option is StringOption) {
            strings.add(option)
        }
    }

    override fun getAnswersCount(): Int {
        return answersCount
    }

    override fun getOptions(): List<Option> {
        return strings.toList()
    }

    override fun completeAnswers(userAnswers: List<Option>) {
        try {
            if (userAnswers.isNullOrEmpty()) {
                throw Error("The question has no answers")
            } else {
                var myAnswer =
                    strings.find { it.optionId == (userAnswers[0] as StringOption).optionId }
                if (myAnswer != null) {
                    setOptionsToNotSelected()
                    myAnswer.isSelected = true
                    answersCount = 1
                } else {
                    throw Error("The answer does not belong to this question")
                }
            }
        } catch (e: Error) {
            throw Error("Error completing the answer options", e)
        }
    }

    private fun setOptionsToNotSelected() {
        strings.forEach {
            it.isSelected = false
        }
    }

    override fun getAnswers(): List<Option> {
        try {
            var myAnswer = strings.find { it.isSelected }
            if (myAnswer != null) {
                return listOf(myAnswer)
            } else {
                throw Error("The question has not been answered")
            }
        } catch (e: Error) {
            throw Error("Error obtaining the answers", e)
        }
    }

}
