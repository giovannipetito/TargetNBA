package com.petito.targetnba.remote

import com.petito.targetnba.domain.Result
import com.petito.targetnba.domain.api.UsersResponse

interface UsersDataSource {
    suspend fun getUsers(page: Int): Result<UsersResponse>
}