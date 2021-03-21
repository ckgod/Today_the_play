package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class NoticeDetailResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: Notice,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Notice(
        @SerializedName("content")
        var content: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("writtenAt")
        var writtenAt: String
    )
}