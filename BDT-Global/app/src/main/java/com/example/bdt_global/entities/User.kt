package com.example.bdt_global.entities

import com.example.bdt_global.entities.responseEntities.ResultsResponse

class User(
    var uid: String = "",
    var name: String = "",
    var surname: String = "",
    var dni: Int = -1,
    var mail: String = ""
) {

    private var results: MutableList<ResultsResponse> = mutableListOf()

    fun addResults(newResults: ResultsResponse) {
        if (newResults != null) {
            results.add(newResults)
        }
    }

    fun getResults(): List<ResultsResponse> {
        return results.toList()
    }

}
