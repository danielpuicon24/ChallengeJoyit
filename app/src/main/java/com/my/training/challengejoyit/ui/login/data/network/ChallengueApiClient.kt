package com.my.training.challengejoyit.ui.login.data.network

import com.my.training.challengejoyit.ui.login.data.model.DataLogin
import com.my.training.challengejoyit.ui.login.data.model.ResponseSuccessSearchUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengueApiClient {

    @POST("/api/login")
    suspend fun postLogin( @Body dataLogin: DataLogin) : Response<Map<String, Any>>

    @GET("/api/users/{userId}")
    suspend fun getSearchUser( @Path("userId") userId: Int) : ResponseSuccessSearchUser

    @POST("/api/register")
    suspend fun postRegister( @Body dataLogin: DataLogin) : Response<Map<String, Any>>
}