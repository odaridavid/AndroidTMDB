package dev.davidodari.androidtmdb.data

import dev.davidodari.androidtmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}")
            .build()
        return chain.proceed(newRequest)
    }
}
