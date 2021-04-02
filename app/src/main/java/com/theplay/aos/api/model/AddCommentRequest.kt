package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class AddCommentRequest(
    @SerializedName("content")
    var content: String,
    @SerializedName("postCommentParentId")
    var postCommentParentId: Int
)