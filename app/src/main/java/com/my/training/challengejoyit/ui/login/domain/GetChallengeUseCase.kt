package com.my.training.challengejoyit.ui.login.domain

import com.my.training.challengejoyit.ui.login.data.ChallengueRepository
import com.my.training.challengejoyit.ui.login.data.model.DataLogin
import com.my.training.challengejoyit.ui.login.data.model.ResponseSuccessSearchUser
import javax.inject.Inject

class GetChallengeUseCase @Inject constructor(
    private val repository: ChallengueRepository
) {

    suspend fun postLogin(dataLogin: DataLogin) : Map<String, Any?>{
        return repository.postLogin(dataLogin)
    }

    suspend fun  postRegister(dataLogin: DataLogin) : Map<String, Any?>{
        return repository.postRegister(dataLogin)
    }

    suspend fun searchUser(userId: Int) : ResponseSuccessSearchUser{
        return repository.getSearchUser(userId)
    }

}