package com.example.cinema

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val API_KEY = "cf3f93c770820648b653a9e763863cb3"

interface AvtorizeApi {
    @GET("authentication/token/new")
    fun createToken(@Query("api_key") api_key: String = API_KEY): Call<CreateRequestToken>

    @POST("authentication/token/validate_with_login")
    fun loginAutorizeApi(@Query("api_key") api_key: String = API_KEY,@Body user :  LogInAut)
}

