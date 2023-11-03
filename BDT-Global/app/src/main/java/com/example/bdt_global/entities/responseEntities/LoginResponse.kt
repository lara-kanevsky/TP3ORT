package com.example.bdt_global.entities.responseEntities

data class LoginResponse(
    val expireDate: String,
    val message: String,
    val isSuccess: Boolean,
    val errors: Errors?,
    val data: String
)

data class Errors(
    val `$`: List<String>?,
    val model: List<String>?
)