package ru.haliksar.libraries.retroflow.adapters

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class FlowCallAdapter<T : Any>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Flow<T> = flow {
        emit(
            suspendCancellableCoroutine { continuation ->
                call.enqueue(object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        try {
                            continuation.resume(response.body()!!)
                        } catch (exc: KotlinNullPointerException) {
                            continuation.resumeWithException(exc)
                        }
                    }
                })
                continuation.invokeOnCancellation { call.cancel() }
            }
        )
    }
}