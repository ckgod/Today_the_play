package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class NoticeResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("list")
    var list: List<NoticeId>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class NoticeId(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String
    )
}