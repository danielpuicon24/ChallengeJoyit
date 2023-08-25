package com.my.training.challengejoyit.ui.login.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSuccessLogin (
    @SerializedName("token") val token: String
        )