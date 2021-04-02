package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class PostLikeResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Data,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Data(
        @SerializedName("likeYn")
        var likeYn: String
    )
}