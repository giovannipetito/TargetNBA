package com.petito.targetnba.remote

import com.petito.targetnba.domain.api.AllTeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is an interface called ApiService that defines an HTTP API endpoint for retrieving a list of
 * all NBA teams from a remote server.
 *
 * The interface defines a single method called getAllTeams that is annotated with @GET("teams").
 * This annotation specifies that the HTTP method used for this API call is GET and that the endpoint
 * URL is "teams".
 *
 * The method takes a single parameter page which is annotated with @Query("page"). This annotation
 * specifies that the parameter will be sent as a query parameter in the request URL with the name "page".
 *
 * The return type of the method is AllTeamsResponse. The suspend modifier before the method signature
 * indicates that this method is a coroutine and can be used with suspend functions in Kotlin.
 *
 * When this method is called, Retrofit (a networking library for Android) will make an HTTP GET
 * request to the specified URL with the provided query parameter. The server will then respond
 * with a JSON payload that will be deserialized into an AllTeamsResponse object by Gson (a JSON
 * serialization/deserialization library for Java). The AllTeamsResponse object contains a list
 * of Team objects representing the NBA teams.
 */
interface ApiService {

    @GET("teams")
    suspend fun getAllTeams(
        @Query("page") page: Int,
    ): AllTeamsResponse
}