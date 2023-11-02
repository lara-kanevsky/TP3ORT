package com.example.bdt_global.entities

import com.google.gson.annotations.SerializedName

class FormType (
    @SerializedName("id") var id: Int,
    @SerializedName("label") var label: String,
    @SerializedName("type") var type: String,
)
