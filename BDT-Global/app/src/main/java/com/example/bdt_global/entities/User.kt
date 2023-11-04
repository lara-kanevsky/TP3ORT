package com.example.bdt_global.entities

import com.example.bdt_global.entities.responseEntities.ResultsResponse

class User(
    var nombre: String,
    var apellido: String,
    var dni: Int,
    var mail: String
) {

    private var myResults = mutableListOf<ResultsResponse>()

    fun addResult(result: ResultsResponse) {
        if (result != null) {
            myResults.add(result)
        }
    }

    fun getResults(): List<ResultsResponse> {
        return myResults.toList()
    }

}
