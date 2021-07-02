package com.theplay.aos.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCreator {
    companion object {
        // 실서버 주소
        var TEST_URL = "http://13.125.103.172:8081/v2/api-docs/"
        var REAL_URL = "http://ec2-15-164-83-29.ap-northeast-2.compute.amazonaws.com:8081/v2/api-docs/"

        private fun defaultRetrofit(): Retrofit {
            var gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(TEST_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
        }

        fun <T> create(service: Class<T>): T {
            return defaultRetrofit().create(service)
        }

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(TokenInterceptor()) // JWT 자동 헤더 전송
                .build()
        }
    }
}