package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName

class QuestionScreenDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("question") val questionScreenResponse: QuestionScreenResponse
)
