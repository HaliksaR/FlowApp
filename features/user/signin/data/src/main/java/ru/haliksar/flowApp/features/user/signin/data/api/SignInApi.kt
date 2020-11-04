package ru.haliksar.flowApp.features.user.signin.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInDto
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.Timeout
import java.util.concurrent.TimeUnit

interface SignInApi {
    companion object {
        const val URL = "/api/v1/signin"
    }

    @POST(URL)
    @Timeout(5, TimeUnit.SECONDS)
    suspend fun signIn(@Body data: SignInDto): AuthDto
}