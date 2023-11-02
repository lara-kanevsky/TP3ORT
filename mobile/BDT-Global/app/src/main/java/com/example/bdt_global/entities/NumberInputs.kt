package com.example.bdt_global.entities

class NumberInputs(
    var inputs: MutableList<NumberInput>
) : AnswerOptions() {

    private var answersCount: Int = 0

    override fun getAnswersCount(): Int {
        return answersCount
    }

    override fun getOptions(): List<Option> {
        return inputs.toList()
    }

    override fun completeAnswers(userAnswers: List<Option>) {
        try {
            if (userAnswers.isNullOrEmpty() || inputs.size != userAnswers.size) {
                throw Error("The question has not the right amount of answers")
            } else {
                inputs.forEachIndexed { index, _ ->
                    inputs[index].userInput = (userAnswers[index] as NumberInput).userInput
                }
                answersCount = inputs.size
            }
        } catch (e: Error) {
            throw Error("Error completing the answer options", e)
        }
    }

    override fun getAnswers(): List<Option> {
        var myAnswers = mutableListOf<NumberInput>()
        try {
            inputs.forEach {
                if (!it.userInput.isNullOrBlank()) {
                    myAnswers.add(it)
                } else {
                    throw Error("There are missing answers")
                }
            }
        } catch (e: Error) {
            throw Error("Error obtaining the answers", e)
        }
        return myAnswers
    }

}
