package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName
import com.example.bdt_global.entities.FormType

class FormTypesDataResponse (
    @SerializedName("response") val response: String,
    @SerializedName("formTypes") val formTypes: List<FormType>
)
