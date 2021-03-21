package com.theplay.aos.api

import android.util.Log
import com.theplay.aos.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.theplay.aos.ApplicationClass.Companion.spToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (spToken == null) {
            Log.e("[Log.e] ", "sp is null in Interceptor")
        }
        val jwtToken: String? = spToken?.getString(X_ACCESS_TOKEN, null)
        Log.e("[Log.e] tag : ", "$jwtToken ")
        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken)
            Log.e("[Log.e] tag : ", "add header")
        }
        return chain.proceed(builder.build())
    }
}