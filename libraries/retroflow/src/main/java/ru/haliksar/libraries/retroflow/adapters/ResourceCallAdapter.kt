package ru.haliksar.libraries.retroflow.adapters

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import ru.haliksar.libraries.retroflow.wrapper.Resource
import java.lang.reflect.Type
import kotlin.coroutines.resume

internal class ResourceCallAdapter<T : Any?>(
    private val responseType: Type
) : CallAdapter<T?, Flow<Resource<T?, Throwable>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T?>): Flow<Resource<T?, Throwable>> = flow {
        emit(Resource.Loading)
        emit(
            suspendCancellableCoroutine { coroutine ->
                call.enqueue(object : Callback<T?> {

                    override fun onFailure(call: Call<T?>, t: Throwable) {
                        coroutine.resume(Resource.Error(t))
                    }

                    override fun onResponse(call: Call<T?>, response: Response<T?>) {
                        coroutine.resume(Resource.Success(response.body()))
                    }

                })
                coroutine.invokeOnCancellation { call.cancel() }
            }
        )
    }
}