package com.example.bdt_global.entities

abstract class AnswerOptions {
    abstract fun addOption(option: Option)
    abstract fun getAnswersCount(): Int
    abstract fun getOptions(): List<Option>
    abstract fun completeAnswers(userAnswers: List<Option>)
    abstract fun getAnswers(): List<Option>
}
