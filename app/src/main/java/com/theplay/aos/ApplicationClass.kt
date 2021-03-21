package com.theplay.aos

import android.app.Application
import android.content.Context
import android.content.SharedPreferences


class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (spToken == null) {
            spToken = applicationContext.getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE)
        }
        if(spLock == null) {
            spLock = applicationContext.getSharedPreferences(LOCK_TOKEN, Context.MODE_PRIVATE)
        }
        if(fcmToken == null) {
            fcmToken = applicationContext.getSharedPreferences(FCM_TOKEN, Context.MODE_PRIVATE)
        }
        if(alarmAllowToken == null) {
            alarmAllowToken = applicationContext.getSharedPreferences(ALARM_ALLOW_TOKEN, Context.MODE_PRIVATE)
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {  // Retrofit 인스턴스
        const val TAG = "ApplicationClass"

        var X_ACCESS_TOKEN = "x-access-token"
        var LOCK_TOKEN = "LOCK-TOKEN"
        var FCM_TOKEN = "FCM-TOKEN"
        var ALARM_ALLOW_TOKEN = "ALARM-ALLOW-TOKEN"
        var instance: ApplicationClass? = null

        var sSharedPreferences: SharedPreferences? = null

        // jwt Token 저장
        var spToken : SharedPreferences? = null

        // 화면 잠금상태 저장
        var spLock: SharedPreferences? = null

        // fcmToken
        var fcmToken : SharedPreferences? = null
        var alarmAllowToken : SharedPreferences? = null
    }
}