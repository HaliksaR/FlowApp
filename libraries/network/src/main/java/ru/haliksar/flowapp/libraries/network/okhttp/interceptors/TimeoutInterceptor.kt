package ru.haliksar.flowapp.libraries.network.okhttp.interceptors

import okhttp3.Interceptor
import retrofit2.Invocation
import java.util.concurrent.TimeUnit


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Timeout(val duration: Int, val unit: TimeUnit)


internal fun timeoutInterceptor() = Interceptor { chain ->
    val request = chain.request()
    val timeout = request
        .tag(Invocation::class.java)
        ?.method()
        ?.getAnnotation(Timeout::class.java)
    if (timeout != null) {
        chain
            .withReadTimeout(timeout.duration, timeout.unit)
            .withConnectTimeout(timeout.duration, timeout.unit)
            .withWriteTimeout(timeout.duration, timeout.unit)
            .proceed(request)
    } else {
        chain.proceed(request)
    }
}
