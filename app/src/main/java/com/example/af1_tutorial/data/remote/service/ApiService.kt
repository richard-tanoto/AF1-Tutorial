package com.example.af1_tutorial.data.remote.service

import com.example.af1_tutorial.data.remote.response.GetUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /*@GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): GetUserResponse*/
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
    ): Call<GetUserResponse>
}