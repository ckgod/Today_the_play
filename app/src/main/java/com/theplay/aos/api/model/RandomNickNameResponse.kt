package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class RandomNickNameResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("list")
    var list: List<Name>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Name(
        @SerializedName("nickname")
        var nickname: String
    )
}