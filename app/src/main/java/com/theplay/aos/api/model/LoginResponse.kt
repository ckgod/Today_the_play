package com.theplay.aos.api.model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @Nullable
    @SerializedName("data")
    var data: String?,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
)