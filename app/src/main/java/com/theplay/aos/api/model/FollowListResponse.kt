package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class FollowListResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("list")
    var list: MutableList<FollowInfo>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class FollowInfo(
        @SerializedName("id")
        var id: Int,
        @SerializedName("nickname")
        var nickname: String
    )
}