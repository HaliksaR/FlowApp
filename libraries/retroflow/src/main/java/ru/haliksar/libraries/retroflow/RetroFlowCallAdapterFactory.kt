package ru.haliksar.libraries.retroflow

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import ru.haliksar.libraries.retroflow.adapters.BodyCallAdapter
import ru.haliksar.libraries.retroflow.adapters.ResourceCallAdapter
import ru.haliksar.libraries.retroflow.adapters.ResponseCallAdapter
import ru.haliksar.libraries.retroflow.wrapper.Resource
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RetroFlowCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create() = RetroFlowCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }
        check(returnType is ParameterizedType) {
            "Flow return type must be parameterized as Flow<Type> or Flow<out Type>"
        }
        return getAdapter(getParameterUpperBound(0, returnType))
    }

    private fun getAdapter(type: Type): CallAdapter<*, *> =
        when (getRawType(type)) {
            Response::class.java -> {
                check(type is ParameterizedType) {
                    "Response must be parameterized as Response<Type> or Response<out Type>"
                }
                ResponseCallAdapter<Any>(type)
            }
            Resource::class.java -> {
                check(type is ParameterizedType) {
                    "Response must be parameterized as Resource<Type> or Resource<out Type>"
                }
                ResourceCallAdapter<Any>(type)
            }
            else -> {
                BodyCallAdapter<Any>(type)
            }
        }
}