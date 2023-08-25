package com.my.training.challengejoyit.ui.login.data.network

import com.my.training.challengejoyit.ui.login.data.model.DataLogin
import com.my.training.challengejoyit.ui.login.data.model.ResponseSuccessSearchUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChallengueService @Inject constructor(private val apiClient: ChallengueApiClient)  {

    suspend fun postLogin(data : DataLogin) :Map<String, Any?>{
        return withContext(Dispatchers.IO){
            val response = apiClient.postLogin(data)
            response.body()!!
        }
    }

    suspend fun postRegister(data : DataLogin) :Map<String, Any?>{
        return withContext(Dispatchers.IO){
            val response = apiClient.postRegister(data)
            response.body()!!
        }
    }

    suspend fun getSearchUser(userId : Int) : ResponseSuccessSearchUser {
        return withContext(Dispatchers.IO){
            val response = apiClient.getSearchUser(userId)
            response
        }
    }
}