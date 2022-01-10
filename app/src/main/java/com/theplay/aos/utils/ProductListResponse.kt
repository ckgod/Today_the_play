package com.theplay.aos.utils


import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("list")
    var list: MutableList<Data>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Data(
        @SerializedName("imageUrl")
        var imageUrl: MutableList<String>,
        @SerializedName("itemId")
        var itemId: Int,
        @SerializedName("limitPrice")
        var limitPrice: Int,
        @SerializedName("over")
        var over: Boolean,
        @SerializedName("sellPrice")
        var sellPrice: Int,
        @SerializedName("title")
        var title: String
    )
}