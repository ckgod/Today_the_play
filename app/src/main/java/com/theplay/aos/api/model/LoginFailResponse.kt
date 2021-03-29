package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class LoginFailResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
)