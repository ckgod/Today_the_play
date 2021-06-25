package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class HotRecipeDetailResponse(
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
        var sort: Sort,
        @SerializedName("totalElements")
        var totalElements: Int,
        @SerializedName("totalPages")
        var totalPages: Int
    )

    data class Content(
        @SerializedName("alcoholTags")
        var alcoholTags: MutableList<AlcoholTag>,
        @SerializedName("comment")
        var comment: String,
        @SerializedName("commentCnt")
        var commentCnt: Int,
        @SerializedName("commentNickname")
        var commentNickname: String,
        @SerializedName("content")
        var content: String,
        @SerializedName("createdDate")
        var createdDate: String,
        @SerializedName("haveRecipeYn")
        var haveRecipeYn: String,
        @SerializedName("images")
        var images: MutableList<Image>,
        @SerializedName("ingredients")
        var ingredients: MutableList<Ingredient>,
        @SerializedName("nickname")
        var nickname: String,
        @SerializedName("postId")
        var postId: Int,
        @SerializedName("postLikeYn")
        var postLikeYn: String,
        @SerializedName("postLikeCnt")
        var postLikeCnt: Int,
        @SerializedName("saveRecipeYn")
        var saveRecipeYn: String,
        @SerializedName("steps")
        var steps: MutableList<Step>,
        @SerializedName("userId")
        var userId: Int
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
    data class Image(
        @SerializedName("filePath")
        var filePath: String,
        @SerializedName("number")
        var number: Int
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
    data class Step(
        @SerializedName("content")
        var content: String,
        @SerializedName("number")
        var number: Int
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