package com.theplay.aos.api.model


import com.google.gson.annotations.SerializedName

data class ModifyPostRequest(
    @SerializedName("alcoholTags")
    var alcoholTags: MutableList<AlcoholTag>,
    @SerializedName("content")
    var content: String,
    @SerializedName("haveRecipeYn")
    var haveRecipeYn: String,
    @SerializedName("ingredients")
    var ingredients: MutableList<Ingredient>,
    @SerializedName("steps")
    var steps: MutableList<Step>
) {
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
    data class Step(
        @SerializedName("content")
        var content: String,
        @SerializedName("number")
        var number: Int
    )
    data class AlcoholTag(
        @SerializedName("color")
        var color: Int,
        @SerializedName("iconName")
        var iconName: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("recipeYn")
        var recipeYn: String
    )
}