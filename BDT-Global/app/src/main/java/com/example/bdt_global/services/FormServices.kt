package com.example.bdt_global.services

import com.example.bdt_global.apis.FormApi
import com.example.bdt_global.entities.FormType
import com.example.bdt_global.entities.Option
import com.example.bdt_global.entities.QuestionScreen
import com.example.bdt_global.entities.responseEntities.FormTypesDataResponse
import com.example.bdt_global.entities.responseEntities.QuestionScreenDataResponse
import com.example.bdt_global.entities.responseEntities.ResultsDataResponse
import com.example.bdt_global.entities.responseEntities.ResultsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FormServices {

    private val myScope = CoroutineScope(Dispatchers.IO)

    private val apiClient: FormApi by lazy {
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(FormApi.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(FormApi::class.java)
    }

    suspend fun getFormOptions(): MutableList<FormType> {
        var formOptions = mutableListOf<FormType>()
        myScope.async {
            val myResponse: Response<FormTypesDataResponse> =
                apiClient.getFormTypes()
            if (myResponse.isSuccessful) {
                val response: FormTypesDataResponse? = myResponse.body()
                if (response != null) {
                    formOptions.addAll(response.formTypes)
                } else {
                    throw Error("Response is null")
                }
            } else {
                throw Error("Request not successful")
            }
        }.await()
        return formOptions
    }

    suspend fun getFirstQuestionScreen(formType: String): QuestionScreen {
        lateinit var firstQuestionScreen: QuestionScreen
        myScope.async {
            val myResponse: Response<QuestionScreenDataResponse> =
                apiClient.getFirstQuestionScreen(formType)
            if (myResponse.isSuccessful) {
                val response: QuestionScreenDataResponse? = myResponse.body()
                if (response != null) {
                    firstQuestionScreen = response.questionScreenResponse.toQuestionScreen()
                } else {
                    throw Error("Response is null")
                }
            } else {
                throw Error("Request not successful")
            }
        }.await()
        return firstQuestionScreen
    }

    suspend fun getNextQuestionScreen(
        formType: String,
        screenId: Int,
        answers: List<Option>
    ): QuestionScreen {
        lateinit var nextQuestionScreen: QuestionScreen
        myScope.async {
            val myResponse: Response<QuestionScreenDataResponse> =
                apiClient.getNextQuestionScreen(
                    formType,
                    screenId.toString(),
                    answers
                )
            if (myResponse.isSuccessful) {
                val response: QuestionScreenDataResponse? = myResponse.body()
                if (response != null) {
                    nextQuestionScreen = response.questionScreenResponse.toQuestionScreen()
                } else {
                    throw Error("Response is null")
                }
            } else {
                throw Error("Request not successful")
            }
        }.await()
        return nextQuestionScreen
    }

    suspend fun getResults(formType: String, userAnswers: MutableList<Option>): ResultsResponse {
        lateinit var results: ResultsResponse
        myScope.async {
            val myResponse: Response<ResultsDataResponse> =
                apiClient.getResults(formType, userAnswers)
            if (myResponse.isSuccessful) {
                val response: ResultsDataResponse? = myResponse.body()
                if (response != null) {
                    results = response.results
                } else {
                    throw Error("Response is null")
                }
            } else {
                throw Error("Request not successful")
            }
        }.await()
        return results
    }

}
