package com.example.bdt_global.entities.responseEntities

import java.text.SimpleDateFormat
import java.util.Date

class ResultsResponse(
    val date: String = SimpleDateFormat("dd/MM/yyyy").format(Date()),
    val tonsCO2: Double = 0.0,
    val equivalences: EquivalencesResponse = EquivalencesResponse()
)
