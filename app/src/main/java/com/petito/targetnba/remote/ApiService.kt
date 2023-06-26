package com.petito.targetnba.remote

import com.petito.targetnba.domain.api.AllTeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("teams")
    suspend fun getAllTeams(
        @Query("page") page: Int,
    ): AllTeamsResponse
}