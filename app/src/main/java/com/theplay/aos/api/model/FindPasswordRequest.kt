package com.theplay.aos.api.model

import com.google.gson.annotations.SerializedName

data class FindPasswordRequest(
    @SerializedName("email")
    var email: String
)