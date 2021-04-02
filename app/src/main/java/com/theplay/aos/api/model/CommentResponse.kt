package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("list")
    var list: List<Comment>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Comment(
        @SerializedName("commentLikeCount")
        var commentLikeCount: Int,
        @SerializedName("commentLikeYn")
        var commentLikeYn: String,
        @SerializedName("content")
        var content: String,
        @SerializedName("nickname")
        var nickname: String,
        @SerializedName("postCommentId")
        var postCommentId: Int,
        @SerializedName("secondComments")
        var secondComments: List<SecondComment>,
        @SerializedName("userId")
        var userId: Int
    )

    data class SecondComment(
        @SerializedName("commentLikeCount")
        var commentLikeCount: Int,
        @SerializedName("commentLikeYn")
        var commentLikeYn: String,
        @SerializedName("content")
        var content: String,
        @SerializedName("nickname")
        var nickname: String,
        @SerializedName("postCommentId")
        var postCommentId: Int,
        @SerializedName("userId")
        var userId: Int
    )
}