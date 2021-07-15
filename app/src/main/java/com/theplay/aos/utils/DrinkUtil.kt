package com.theplay.aos.utils

import com.theplay.aos.api.model.AddPostRequest

object DrinkUtil {
    var stepList : MutableList<AddPostRequest.Step> = mutableListOf()
    var materialList : MutableList<AddPostRequest.Ingredient> = mutableListOf()
    var DrinkName : String = ""

    fun clearRecipeSaved() {
        stepList = mutableListOf()
        materialList = mutableListOf()
        DrinkName = ""
    }

    fun checkSavedRecipe() : Boolean {
        return materialList.isNotEmpty()
    }

    fun convertIconFromColor(icon : String, color : Int) : String {
        var ret : String = icon
        when(icon.toInt()) {
            in 1..11 -> { // 소주
                when(color) {
                    0 -> ret = "1"
                    1 -> ret = "2"
                    2 -> ret = "3"
                    3 -> ret = "4"
                    4 -> ret = "5"
                    5 -> ret = "6"
                    6 -> ret = "7"
                    7 -> ret = "8"
                    8 -> ret = "9"
                    9 -> ret = "10"
                }
            }
            in 12..22 -> { // 와인
                when(color) {
                    0 -> ret = "12"
                    1 -> ret = "13"
                    2 -> ret = "14"
                    3 -> ret = "15"
                    4 -> ret = "16"
                    5 -> ret = "17"
                    6 -> ret = "18"
                    7 -> ret = "19"
                    8 -> ret = "20"
                    9 -> ret = "21"
                }
            }
            in 23..33 -> { // 칵테일
                when(color) {
                    0 -> ret = "23"
                    1 -> ret = "24"
                    2 -> ret = "25"
                    3 -> ret = "26"
                    4 -> ret = "27"
                    5 -> ret = "28"
                    6 -> ret = "29"
                    7 -> ret = "30"
                    8 -> ret = "31"
                    9 -> ret = "32"
                }
            }
            in 34..44 -> { // 와인2
                when(color) {
                    0 -> ret = "34"
                    1 -> ret = "35"
                    2 -> ret = "36"
                    3 -> ret = "37"
                    4 -> ret = "38"
                    5 -> ret = "39"
                    6 -> ret = "40"
                    7 -> ret = "41"
                    8 -> ret = "42"
                    9 -> ret = "43"
                }
            }
            in 45..55 -> { // 보드카
                when(color) {
                    0 -> ret = "45"
                    1 -> ret = "46"
                    2 -> ret = "47"
                    3 -> ret = "48"
                    4 -> ret = "49"
                    5 -> ret = "50"
                    6 -> ret = "51"
                    7 -> ret = "52"
                    8 -> ret = "53"
                    9 -> ret = "54"
                }
            }
            in 56..66 -> { // 사케
                when(color) {
                    0 -> ret = "56"
                    1 -> ret = "57"
                    2 -> ret = "58"
                    3 -> ret = "59"
                    4 -> ret = "60"
                    5 -> ret = "61"
                    6 -> ret = "62"
                    7 -> ret = "63"
                    8 -> ret = "64"
                    9 -> ret = "65"
                }
            }
            in 67..77 -> { // 샷
                when(color) {
                    0 -> ret = "67"
                    1 -> ret = "68"
                    2 -> ret = "69"
                    3 -> ret = "70"
                    4 -> ret = "71"
                    5 -> ret = "72"
                    6 -> ret = "73"
                    7 -> ret = "74"
                    8 -> ret = "75"
                    9 -> ret = "76"
                }
            }
            in 78..88 -> { // 캔
                when(color) {
                    0 -> ret = "78"
                    1 -> ret = "79"
                    2 -> ret = "80"
                    3 -> ret = "81"
                    4 -> ret = "82"
                    5 -> ret = "83"
                    6 -> ret = "84"
                    7 -> ret = "85"
                    8 -> ret = "86"
                    9 -> ret = "87"
                }
            }
            in 89..99 -> { // 맥주
                when(color) {
                    0 -> ret = "89"
                    1 -> ret = "90"
                    2 -> ret = "91"
                    3 -> ret = "92"
                    4 -> ret = "93"
                    5 -> ret = "94"
                    6 -> ret = "95"
                    7 -> ret = "96"
                    8 -> ret = "97"
                    9 -> ret = "98"
                }
            }
            in 100..105 -> { // 재료

            }
        }
        return ret
    }
}