package ru.haliksar.flowApp.features.user.signin.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInDto

interface SignInApi {
    companion object {
        const val URL = "/api/v1/signin"
    }

    @POST(URL)
    fun signIn(@Body data: SignInDto): Flow<AuthDto>
}