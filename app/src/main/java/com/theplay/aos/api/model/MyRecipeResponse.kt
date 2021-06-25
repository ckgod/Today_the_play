package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class MyRecipeResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Data,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
) {
    data class Content(
        @SerializedName("alcoholTag")
        var alcoholTag: AlcoholTag,
        @SerializedName("ingredients")
        var ingredients: MutableList<Ingredient>,
        @SerializedName("postId")
        var postId: Int
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
    data class AlcoholTag(
        @SerializedName("color")
        var color: Int,
        @SerializedName("iconName")
        var iconName: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("recipeYn")
        var recipeYn: String
    )
    data class Data(
        @SerializedName("content")
        var content: MutableList<Content>,
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
        var sort: SortX,
        @SerializedName("totalElements")
        var totalElements: Int,
        @SerializedName("totalPages")
        var totalPages: Int
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
    data class SortX(
        @SerializedName("empty")
        var empty: Boolean,
        @SerializedName("sorted")
        var sorted: Boolean,
        @SerializedName("unsorted")
        var unsorted: Boolean
    )
}