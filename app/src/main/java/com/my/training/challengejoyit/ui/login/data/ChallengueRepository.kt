package com.my.training.challengejoyit.ui.login.data

import com.my.training.challengejoyit.ui.login.data.model.DataLogin
import com.my.training.challengejoyit.ui.login.data.model.ResponseSuccessSearchUser
import com.my.training.challengejoyit.ui.login.data.network.ChallengueService
import javax.inject.Inject

class ChallengueRepository @Inject constructor(
    private val service: ChallengueService
){

    suspend fun postLogin(dataLogin: DataLogin) : Map<String, Any?>{
        return service.postLogin(dataLogin)
    }

    suspend fun postRegister(dataLogin: DataLogin): Map<String,Any?>{
        return service.postRegister(dataLogin)
    }
    suspend fun getSearchUser(userId: Int) : ResponseSuccessSearchUser {
        return service.getSearchUser(userId)
    }
}