package com.example.bdt_global.entities

class NumberInput(
    type: String,
    optionId: Int,
    label: String,
    value: Float,
    nextScreenNavigationId: String,
    nextDefaultScreenNavigationId: String,
    screenId: Int,
    measureUnit: String,
    var userInput: String = ""
) : Option(
    type,
    optionId,
    label,
    value,
    nextScreenNavigationId,
    nextDefaultScreenNavigationId,
    screenId,
    measureUnit
)
