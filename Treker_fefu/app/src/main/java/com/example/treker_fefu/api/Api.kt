package com.example.treker_fefu.api;

import com.example.treker_fefu.api.model.Token;
import com.example.treker_fefu.api.model.User;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface API {
    @GET("api/user/profile")
    suspend fun getProfile(): User

    @POST("api/auth/register")
    suspend fun register(
        @Query("login") login: String,
        @Query("password") pass: String,
        @Query("name") name: String,
        @Query("gender") gender: Int,
    ): Token

    @POST("api/auth/login")
    suspend fun login(
        @Query("login") login: String,
        @Query("password") password: String,
    ): Token

    @POST("api/auth/logout")
    suspend fun logout(): Unit
}