package com.petito.targetnba.domain.api

import com.google.gson.annotations.SerializedName

class AllTeamsResponse {

    @SerializedName("data")
    var teams: List<Team>? = null

    @SerializedName("meta")
    var meta: Meta? = null

    class Team {

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("abbreviation")
        var abbreviation: String? = null

        @SerializedName("city")
        var city: String? = null

        @SerializedName("conference")
        var conference: String? = null

        @SerializedName("division")
        var division: String? = null

        @SerializedName("full_name")
        var full_name: String? = null

        @SerializedName("name")
        var name: String? = null
    }

    class Meta {

        @SerializedName("total_pages")
        var total_pages: Int? = null

        @SerializedName("current_page")
        var current_page: Int? = null

        @SerializedName("next_page")
        var next_page: Int? = null

        @SerializedName("per_page")
        var per_page: Int? = null

        @SerializedName("total_count")
        var total_count: Int? = null
    }
}