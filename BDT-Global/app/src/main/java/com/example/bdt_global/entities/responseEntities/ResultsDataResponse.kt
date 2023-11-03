package com.example.bdt_global.entities.responseEntities

import com.google.gson.annotations.SerializedName

class ResultsDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: ResultsResponse
)
