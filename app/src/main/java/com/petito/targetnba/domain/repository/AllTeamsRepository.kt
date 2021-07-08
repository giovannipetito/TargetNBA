package com.petito.targetnba.domain.repository

import com.petito.targetnba.di.ApiInfo
import com.petito.targetnba.domain.api.AllTeamsResponse
import com.petito.targetnba.domain.Result
import com.petito.targetnba.remote.AllTeamsDataSource
import com.petito.targetnba.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllTeamsRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : AllTeamsDataSource {
    override suspend fun getAllTeams(page: Int): Result<AllTeamsResponse> {
        return try {
            val allTeamsResponse = apiService.getAllTeams(0)
            Result.Success(allTeamsResponse)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}