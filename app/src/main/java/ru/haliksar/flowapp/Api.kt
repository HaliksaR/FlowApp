package ru.haliksar.flowapp

import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

data class Test(
    @SerializedName("id") val id: Int,
    @SerializedName("data") val data: String
)

interface Api {
    @GET("/api/test")
    fun getRx(): Observable<Test>

    @GET("/api/test")
    fun getFlow(): Flow<Test>
}