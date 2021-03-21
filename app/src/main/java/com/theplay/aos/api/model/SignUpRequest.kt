package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("confirmPassword")
    var confirmPassword: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("password")
    var password: String
)