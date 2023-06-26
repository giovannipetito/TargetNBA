package com.petito.targetnba.domain.repository

import com.petito.targetnba.di.ApiInfo
import com.petito.targetnba.domain.api.UsersResponse
import com.petito.targetnba.domain.Result
import com.petito.targetnba.remote.UsersDataSource
import com.petito.targetnba.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
    ) : UsersDataSource {

    override suspend fun getUsers(page: Int): Result<UsersResponse> {
        return try {
            val usersResponse: UsersResponse = apiService.getUsers(0)
            Result.Success(usersResponse)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}