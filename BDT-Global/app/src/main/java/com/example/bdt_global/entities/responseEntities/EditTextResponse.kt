package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName

open class EditTextResponse(
    type: String,
    optionId: Int,
    label: String,
    value: Float,
    nextScreenNavigationId: String,
    nextDefaultScreenNavigationId: String,
    screenId: Int,
    measureUnit: String,
    @SerializedName("userInput") var userInput: Float = -1f
) :
    OptionResponse(
        type,
        optionId,
        label,
        value,
        nextScreenNavigationId,
        nextDefaultScreenNavigationId,
        screenId,
        measureUnit
    )
