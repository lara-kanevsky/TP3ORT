package com.example.bdt_global.entities

class StringOption(
    type: String,
    optionId: Int,
    label: String,
    value: Float,
    nextScreenNavigationId: String,
    nextDefaultScreenNavigationId: String,
    screenId: Int,
    measureUnit: String
) :
    Option(
        type,
        optionId,
        label,
        value,
        nextScreenNavigationId,
        nextDefaultScreenNavigationId,
        screenId,
        measureUnit
    ) {
    var isSelected: Boolean = false
}
