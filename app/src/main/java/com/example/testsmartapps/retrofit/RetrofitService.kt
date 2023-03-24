package com.example.testsmartapps.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("test.txt")
    suspend fun getResponse(): Boolean

}