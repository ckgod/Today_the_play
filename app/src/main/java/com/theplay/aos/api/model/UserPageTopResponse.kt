package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class UserPageTopResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Result,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Result(
        @SerializedName("followers")
        var followers: Int,
        @SerializedName("likes")
        var likes: Int,
        @SerializedName("nickname")
        var nickname: String,
        @SerializedName("posts")
        var posts: Int,
        @SerializedName("recipes")
        var recipes: Int,
        @SerializedName("followingYn")
        var followingYn : String
    )
}