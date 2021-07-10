package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class CommentLikeResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("data")
    var data : Result
) {
    data class Result(
        @SerializedName("likeYn")
        var likeYn : String
    )
}