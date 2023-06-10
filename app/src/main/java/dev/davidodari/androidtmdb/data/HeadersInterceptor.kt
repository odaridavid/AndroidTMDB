package dev.davidodari.androidtmdb.data

import dev.davidodari.androidtmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}")
            .build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
