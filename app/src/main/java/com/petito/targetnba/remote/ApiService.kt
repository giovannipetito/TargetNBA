package com.petito.targetnba.remote

import com.petito.targetnba.domain.api.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/users")
    fun getUsers(
        @Query("page") page: Int,
    ): UsersResponse
}