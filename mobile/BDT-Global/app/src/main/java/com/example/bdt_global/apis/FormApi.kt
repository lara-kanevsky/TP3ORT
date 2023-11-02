package com.example.bdt_global.apis

import com.example.bdt_global.entities.Option
import com.example.bdt_global.entities.responseEntities.FormTypesDataResponse
import com.example.bdt_global.entities.responseEntities.QuestionScreenDataResponse
import com.example.bdt_global.entities.responseEntities.QuestionScreenResponse
import com.example.bdt_global.entities.responseEntities.ResultsDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Body

interface FormApi {

    companion object {
        const val BASE_URL = "http://10.0.2.2:3000/"
    }

    @GET("forms")
    suspend fun getFormTypes(): Response<FormTypesDataResponse>

    @GET("forms/{type}")
    suspend fun getFirstQuestionScreen(@Path("type") formType: String): Response<QuestionScreenDataResponse>

    @POST("forms/{type}/{screenId}")
    @JvmSuppressWildcards
    suspend fun getNextQuestionScreen(@Path("type") formType: String, @Path("screenId") id: String, @Body answers: List<Option>): Response<QuestionScreenDataResponse>

    @POST("forms/{type}")
    suspend fun getResults(@Path("type") formType: String, @Body userAnswers: MutableList<Option>): Response<ResultsDataResponse>

}
