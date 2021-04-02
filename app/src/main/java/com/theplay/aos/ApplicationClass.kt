package com.theplay.aos

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse


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

        var emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        var X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        var LOCK_TOKEN = "LOCK-TOKEN"
        var FCM_TOKEN = "FCM-TOKEN"
        var ALARM_ALLOW_TOKEN = "ALARM-ALLOW-TOKEN"
        var instance: ApplicationClass? = null

        var sSharedPreferences: SharedPreferences? = null

        // user정보 저장
        var userInfo : MyPageTopResponse? = null
        // mainboard 게시글들 저장
        var mainBoardList : MutableList<MainBoardResponse.Content>? = null
        var myLikedPost : MutableList<MainBoardResponse.Content>? = null
        var myPostedPost : MutableList<MainBoardResponse.Content>? = null

        // jwt Token 저장
        var spToken : SharedPreferences? = null

        // 화면 잠금상태 저장
        var spLock: SharedPreferences? = null

        // fcmToken
        var fcmToken : SharedPreferences? = null
        var alarmAllowToken : SharedPreferences? = null

        val colorHashMap = mapOf<Int,Int>(
            0 to R.color.colorWhite,
            1 to R.color.ingre,
            2 to R.color.ingre2,
            3 to R.color.ingre3,
            4 to R.color.ingre4,
            5 to R.color.ingre5,
            6 to R.color.ingre6,
            7 to R.color.ingre7,
            8 to R.color.ingre8,
            9 to R.color.mainColor
        )

        val colorToCodeHashMap = mapOf<Int,Int>(
            R.color.colorWhite to 0,
            R.color.ingre to 1,
            R.color.ingre2 to 2,
            R.color.ingre3 to 3,
            R.color.ingre4 to 4,
            R.color.ingre5 to 5,
            R.color.ingre6 to 6,
            R.color.ingre7 to 7,
            R.color.ingre8 to 8,
            R.color.mainColor to 9
        )

        val iconHashMap = mapOf<String, Int>(
            "1" to R.drawable.ic_drinks_soju_main,
            "2" to R.drawable.drinks_soju_0,
            "3" to R.drawable.drinks_soju_1,
            "4" to R.drawable.drinks_soju_2,
            "5" to R.drawable.drinks_soju_3,
            "6" to R.drawable.drinks_soju_4,
            "7" to R.drawable.drinks_soju_5,
            "8" to R.drawable.drinks_soju_6,
            "9" to R.drawable.drinks_soju_7,
            "01" to R.drawable.ic_drinks_soju_main,
            "02" to R.drawable.drinks_soju_0,
            "03" to R.drawable.drinks_soju_1,
            "04" to R.drawable.drinks_soju_2,
            "05" to R.drawable.drinks_soju_3,
            "06" to R.drawable.drinks_soju_4,
            "07" to R.drawable.drinks_soju_5,
            "08" to R.drawable.drinks_soju_6,
            "09" to R.drawable.drinks_soju_7,
            "10" to R.drawable.drinks_soju_8,
            "11" to R.drawable.drinks_soju_b,
            "12" to R.drawable.drinks_wine_main,
            "13" to R.drawable.drinks_wine_0,
            "14" to R.drawable.drinks_wine_1,
            "15" to R.drawable.drinks_wine_2,
            "16" to R.drawable.drinks_wine_3,
            "17" to R.drawable.drinks_wine_4,
            "18" to R.drawable.drinks_wine_5,
            "19" to R.drawable.drinks_wine_6,
            "20" to R.drawable.drinks_wine_7,
            "21" to R.drawable.drinks_wine_8,
            "22" to R.drawable.drinks_wine_b,
            "23" to R.drawable.drinks_cock_main,
            "24" to R.drawable.drinks_cock_0,
            "25" to R.drawable.drinks_cock_1,
            "26" to R.drawable.drinks_cock_2,
            "27" to R.drawable.drinks_cock_3,
            "28" to R.drawable.drinks_cock_4,
            "29" to R.drawable.drinks_cock_5,
            "30" to R.drawable.drinks_cock_6,
            "31" to R.drawable.drinks_cock_7,
            "32" to R.drawable.drinks_cock_8,
            "33" to R.drawable.drinks_cock_b,
            "34" to R.drawable.drinks_wine_2_main,
            "35" to R.drawable.drinks_wine_2_0,
            "36" to R.drawable.drinks_wine_2_1,
            "37" to R.drawable.drinks_wine_2_2,
            "38" to R.drawable.drinks_wine_2_3,
            "39" to R.drawable.drinks_wine_2_4,
            "40" to R.drawable.drinks_wine_2_5,
            "41" to R.drawable.drinks_wine_2_6,
            "42" to R.drawable.drinks_wine_2_7,
            "43" to R.drawable.drinks_wine_2_8,
            "44" to R.drawable.drinks_wine_2_b,
            "45" to R.drawable.drinks_vod_main,
            "46" to R.drawable.drinks_vod_0,
            "47" to R.drawable.drinks_vod_1,
            "48" to R.drawable.drinks_vod_2,
            "49" to R.drawable.drinks_vod_3,
            "50" to R.drawable.drinks_vod_4,
            "51" to R.drawable.drinks_vod_5,
            "52" to R.drawable.drinks_vod_6,
            "53" to R.drawable.drinks_vod_7,
            "54" to R.drawable.drinks_vod_8,
            "55" to R.drawable.drinks_vod_b,
            "56" to R.drawable.drinks_sake_main,
            "57" to R.drawable.drinks_sake_0,
            "58" to R.drawable.drinks_sake_1,
            "59" to R.drawable.drinks_sake_2,
            "60" to R.drawable.drinks_sake_3,
            "61" to R.drawable.drinks_sake_4,
            "62" to R.drawable.drinks_sake_5,
            "63" to R.drawable.drinks_sake_6,
            "64" to R.drawable.drinks_sake_7,
            "65" to R.drawable.drinks_sake_8,
            "66" to R.drawable.drinks_sake_b,
            "67" to R.drawable.drinks_shot_main,
            "68" to R.drawable.drinks_shot_0,
            "69" to R.drawable.drinks_shot_1,
            "70" to R.drawable.drinks_shot_2,
            "71" to R.drawable.drinks_shot_3,
            "72" to R.drawable.drinks_shot_4,
            "73" to R.drawable.drinks_shot_5,
            "74" to R.drawable.drinks_shot_6,
            "75" to R.drawable.drinks_shot_7,
            "76" to R.drawable.drinks_shot_8,
            "77" to R.drawable.drinks_shot_b,
            "78" to R.drawable.drinks_can_main,
            "79" to R.drawable.drinks_can_0,
            "80" to R.drawable.drinks_can_1,
            "81" to R.drawable.drinks_can_2,
            "82" to R.drawable.drinks_can_3,
            "83" to R.drawable.drinks_can_4,
            "84" to R.drawable.drinks_can_5,
            "85" to R.drawable.drinks_can_6,
            "86" to R.drawable.drinks_can_7,
            "87" to R.drawable.drinks_can_8,
            "88" to R.drawable.drinks_can_b,
            "89" to R.drawable.drinks_beer_main,
            "90" to R.drawable.drinks_beer_0,
            "91" to R.drawable.drinks_beer_1,
            "92" to R.drawable.drinks_beer_2,
            "93" to R.drawable.drinks_beer_3,
            "94" to R.drawable.drinks_beer_4,
            "95" to R.drawable.drinks_beer_5,
            "96" to R.drawable.drinks_beer_6,
            "97" to R.drawable.drinks_beer_7,
            "98" to R.drawable.drinks_beer_8,
            "99" to R.drawable.drinks_beer_b,
            "100" to R.drawable.drinks_cherry,
            "101" to R.drawable.drinks_cherry_m,
            "102" to R.drawable.drinks_leaf,
            "103" to R.drawable.drinks_leaf_m,
            "104" to R.drawable.drinks_lemon,
            "105" to R.drawable.drinks_lemon_m
        )

    }
}