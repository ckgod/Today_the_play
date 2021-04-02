package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class RecipeSaveResponse(
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
        @SerializedName("saveYn")
        var saveYn: String
    )
}