package ru.haliksar.flowApp.features.user.signin.data.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("accessToken") val accessToken: String
)