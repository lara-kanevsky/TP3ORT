package com.example.bdt_global.entities

import com.google.gson.annotations.SerializedName

class FormType (
    @SerializedName("formSharingId") var formSharingId: Int,
    @SerializedName("formPersonType") var formPersonType: String,
    @SerializedName("label") var label: String,
    @SerializedName("screenCount") var screenCount: Int
)
