package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class ChangeNickRequest(
    @SerializedName("nickname")
    var nickname : String
)