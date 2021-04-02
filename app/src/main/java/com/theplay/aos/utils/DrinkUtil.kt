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
                    0 -> ret = "2"
                    1 -> ret = "3"
                    2 -> ret = "4"
                    3 -> ret = "5"
                    4 -> ret = "6"
                    5 -> ret = "7"
                    6 -> ret = "8"
                    7 -> ret = "9"
                    8 -> ret = "10"
                    9 -> ret = "1"
                }
            }
            in 12..22 -> { // 와인
                when(color) {
                    0 -> ret = "13"
                    1 -> ret = "14"
                    2 -> ret = "15"
                    3 -> ret = "16"
                    4 -> ret = "17"
                    5 -> ret = "18"
                    6 -> ret = "19"
                    7 -> ret = "20"
                    8 -> ret = "21"
                    9 -> ret = "12"
                }
            }
            in 23..33 -> { // 칵테일
                when(color) {
                    0 -> ret = "24"
                    1 -> ret = "25"
                    2 -> ret = "26"
                    3 -> ret = "27"
                    4 -> ret = "28"
                    5 -> ret = "29"
                    6 -> ret = "30"
                    7 -> ret = "31"
                    8 -> ret = "32"
                    9 -> ret = "23"
                }
            }
            in 34..44 -> { // 와인2
                when(color) {
                    0 -> ret = "35"
                    1 -> ret = "36"
                    2 -> ret = "37"
                    3 -> ret = "38"
                    4 -> ret = "39"
                    5 -> ret = "40"
                    6 -> ret = "41"
                    7 -> ret = "42"
                    8 -> ret = "43"
                    9 -> ret = "34"
                }
            }
            in 45..55 -> { // 보드카
                when(color) {
                    0 -> ret = "46"
                    1 -> ret = "47"
                    2 -> ret = "48"
                    3 -> ret = "49"
                    4 -> ret = "50"
                    5 -> ret = "51"
                    6 -> ret = "52"
                    7 -> ret = "53"
                    8 -> ret = "54"
                    9 -> ret = "45"
                }
            }
            in 56..66 -> { // 사케
                when(color) {
                    0 -> ret = "57"
                    1 -> ret = "58"
                    2 -> ret = "59"
                    3 -> ret = "60"
                    4 -> ret = "61"
                    5 -> ret = "62"
                    6 -> ret = "63"
                    7 -> ret = "64"
                    8 -> ret = "65"
                    9 -> ret = "56"
                }
            }
            in 67..77 -> { // 샷
                when(color) {
                    0 -> ret = "68"
                    1 -> ret = "69"
                    2 -> ret = "70"
                    3 -> ret = "71"
                    4 -> ret = "72"
                    5 -> ret = "73"
                    6 -> ret = "74"
                    7 -> ret = "75"
                    8 -> ret = "76"
                    9 -> ret = "67"
                }
            }
            in 78..88 -> { // 캔
                when(color) {
                    0 -> ret = "79"
                    1 -> ret = "80"
                    2 -> ret = "81"
                    3 -> ret = "82"
                    4 -> ret = "83"
                    5 -> ret = "84"
                    6 -> ret = "85"
                    7 -> ret = "86"
                    8 -> ret = "87"
                    9 -> ret = "78"
                }
            }
            in 89..99 -> { // 맥주
                when(color) {
                    0 -> ret = "90"
                    1 -> ret = "91"
                    2 -> ret = "92"
                    3 -> ret = "93"
                    4 -> ret = "94"
                    5 -> ret = "95"
                    6 -> ret = "96"
                    7 -> ret = "97"
                    8 -> ret = "98"
                    9 -> ret = "89"
                }
            }
            in 100..105 -> { // 재료

            }
        }
        return ret
    }
}