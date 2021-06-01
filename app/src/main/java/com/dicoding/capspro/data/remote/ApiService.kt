package com.dicoding.capspro.data.remote

import com.dicoding.capspro.data.model.*
import com.dicoding.capspro.data.remote.forum.ReportList
import com.dicoding.capspro.data.remote.forum.ThreadList
import com.dicoding.capspro.data.remote.forum.UserList
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("mapbox.places/{query}.json")
    suspend fun getCountry(
        @Path("query") query: String,
        @Query("access_token") accessToken: String,
        @Query("autocomplete") autoComplete: Boolean = true
    ): PlaceResponse
    @FormUrlEncoded
    @POST("user")
    suspend fun addUser(@Field("name") name:String,@Field("email") email: String)
    @GET("user/{email}")
    suspend fun getUser(@Path("email") email: String): UserList
    @GET("report/{email}")
    suspend fun getUserReport(@Path("email") email:String): ReportList
    @GET("thread")
    fun getThread(): Call<ThreadList>
    @DELETE("thread/{id}")
    suspend fun deleteThread(@Path("id") id:String)
}