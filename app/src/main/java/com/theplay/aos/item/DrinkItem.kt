package com.theplay.aos.item

class DrinkItem (
    var icon : Int,
    var name : String,
    var hasRecipe : Boolean,
    var colorType : Int,
    var materialList : MutableList<RecipeMaterialItem>
){}