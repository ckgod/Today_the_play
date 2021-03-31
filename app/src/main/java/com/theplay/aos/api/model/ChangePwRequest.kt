package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class ChangePwRequest(
    @SerializedName("confirmPassword")
    var confirmPassword: String,
    @SerializedName("newPassword")
    var newPassword: String,
    @SerializedName("password")
    var password: String
)