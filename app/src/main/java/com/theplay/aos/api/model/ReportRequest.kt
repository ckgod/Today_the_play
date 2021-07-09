package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class ReportRequest(
    @SerializedName("content")
    var content: String,
    @SerializedName("postId")
    var postId: Int
)