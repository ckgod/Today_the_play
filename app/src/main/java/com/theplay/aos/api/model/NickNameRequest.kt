package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class NickNameRequest(
    @SerializedName("nickname")
    var nickname: String
)