package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName

open class OptionResponse(
    @SerializedName("type") var type: String,
    @SerializedName("optionId") var optionId: Int,
    @SerializedName("label") var label: String,
    @SerializedName("value") var value: Float,
    @SerializedName("nextScreenNavigationId") var nextScreenNavigationId: String,
    @SerializedName("nextDefaultScreenNavigationId") var nextDefaultScreenNavigationId: String,
    @SerializedName("screenId") var screenId: Int,
    @SerializedName("measureUnit") var measureUnit: String
)
