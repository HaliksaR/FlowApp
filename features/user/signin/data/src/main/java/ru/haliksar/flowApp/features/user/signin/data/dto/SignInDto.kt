package ru.haliksar.flowApp.features.user.signin.data.dto

import com.google.gson.annotations.SerializedName

data class SignInDto(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)