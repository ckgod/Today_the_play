package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class HotRecipeResponse(
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
        @SerializedName("content")
        var content: List<Content>,
        @SerializedName("empty")
        var empty: Boolean,
        @SerializedName("first")
        var first: Boolean,
        @SerializedName("last")
        var last: Boolean,
        @SerializedName("number")
        var number: Int,
        @SerializedName("numberOfElements")
        var numberOfElements: Int,
        @SerializedName("pageable")
        var pageable: Pageable,
        @SerializedName("size")
        var size: Int,
        @SerializedName("sort")
        var sort: Sort,
        @SerializedName("totalElements")
        var totalElements: Int,
        @SerializedName("totalPages")
        var totalPages: Int
    )
    data class Content(
        @SerializedName("alcoholTagName")
        var alcoholTagName: String,
        @SerializedName("images")
        var images: List<String>,
        @SerializedName("ingredients")
        var ingredients: List<Ingredient>
    )
    data class Ingredient(
        @SerializedName("color")
        var color: Int,
        @SerializedName("iconName")
        var iconName: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("quantity")
        var quantity: String
    )
    data class Pageable(
        @SerializedName("offset")
        var offset: Int,
        @SerializedName("pageNumber")
        var pageNumber: Int,
        @SerializedName("pageSize")
        var pageSize: Int,
        @SerializedName("paged")
        var paged: Boolean,
        @SerializedName("sort")
        var sort: Sort,
        @SerializedName("unpaged")
        var unpaged: Boolean
    )
    data class Sort(
        @SerializedName("empty")
        var empty: Boolean,
        @SerializedName("sorted")
        var sorted: Boolean,
        @SerializedName("unsorted")
        var unsorted: Boolean
    )
}