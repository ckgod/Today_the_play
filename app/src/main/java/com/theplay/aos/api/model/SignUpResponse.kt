package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: String,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
)