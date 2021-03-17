package com.theplay.aos.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import kotlin.math.ceil


object ViewUtils {
    fun getStatusBarHeight(context: Context?): Int {
        if (context == null) {
            return 0
        }
        val resources: Resources = context.resources ?: return 0
        var result = -1
        val baseSize = 25
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        result = context.resources.getDimensionPixelSize(resourceId)
        // 정상적으로 상태바의 높이를 못가져 온 경우
        if (result < 0) {
            result = ceil(baseSize * resources.displayMetrics.density).toInt()
        }
        return result
    }

    fun convertPixelsToDp(px : Float, context : Context) : Float{
        val resources : Resources = context.resources
        val metrics : DisplayMetrics = resources.displayMetrics
        return px / (metrics.densityDpi / 160f)
    }

    fun convertDpToPixel(dp : Float, context : Context ) : Float{
        val resources : Resources = context.resources
        val metrics : DisplayMetrics = resources.displayMetrics
        return dp * (metrics.densityDpi / 160f)
    }

    fun getScreenSize(activity: Activity): Point? {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    fun getStandardSize_X(activity: Activity) : Int {
        var standardSize_X = 0
        var density = 0f
        val ScreenSize = getScreenSize(activity)
        ScreenSize?.let {
            density = activity.resources.getDisplayMetrics().density
            standardSize_X = (it.x / density).toInt()
        }
        return standardSize_X
    }

    fun getStandardSize_Y(activity: Activity) : Int {
        var standardSize_Y = 0
        var density = 0f
        val ScreenSize = getScreenSize(activity)
        ScreenSize?.let {
            density = activity.resources.getDisplayMetrics().density
            standardSize_Y = (it.y / density).toInt()
        }
        return standardSize_Y
    }

    fun getWindowWidth(activity: Activity) : Int{
        val disPlayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(disPlayMetrics)
        return disPlayMetrics.widthPixels
    }
    fun getWindowHeight(activity: Activity) : Int{
        val disPlayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(disPlayMetrics)
        return disPlayMetrics.heightPixels
    }

    fun dynamicView(context: Context) {
        val displaymetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
        //if you need three fix imageview in width
        //if you need three fix imageview in width
        val devicewidth = displaymetrics.widthPixels / 3
        //if you need 4-5-6 anything fix imageview in height
        //if you need 4-5-6 anything fix imageview in height
        val deviceheight = displaymetrics.heightPixels / 4
//        holder.image_view.getLayoutParams().width = devicewidth
//
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//
//        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
//        holder.image_view.getLayoutParams().height = deviceheight
    }






}