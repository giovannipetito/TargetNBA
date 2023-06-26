package com.petito.targetnba.domain.api

import com.google.gson.annotations.SerializedName

class Support {

    @SerializedName("url")
    var url: String? = null

    @SerializedName("text")
    var text: String? = null
}