package com.petito.targetnba.remote

import com.petito.targetnba.domain.Result
import com.petito.targetnba.domain.api.AllTeamsResponse

interface AllTeamsDataSource {
    suspend fun getAllTeams(page: Int): Result<AllTeamsResponse>
}