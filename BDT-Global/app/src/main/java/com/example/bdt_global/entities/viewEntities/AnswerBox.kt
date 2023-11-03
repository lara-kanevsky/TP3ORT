package com.example.bdt_global.entities.viewEntities

import com.example.bdt_global.entities.Option

interface AnswerBox {
    fun isCompleted(): Boolean
    fun checkCompleted(): Boolean
    fun getAnswers(): List<Option>
}
