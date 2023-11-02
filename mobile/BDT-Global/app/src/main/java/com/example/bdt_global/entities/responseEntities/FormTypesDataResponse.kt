package com.example.bdt_global.entities.responseEntities

import com.example.bdt_global.entities.FormType
import com.google.gson.annotations.SerializedName

class FormTypesDataResponse (
    @SerializedName("response") val response: String,
    @SerializedName("formTypes") val formTypes: List<FormType>
)
