package com.my.training.challengejoyit.ui.login.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSuccessSearchUser(
    val data: UserData,
    val support: SupportData
)

data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class SupportData(
    val url: String,
    val text: String
)
