package com.dicoding.capspro.data.remote

import com.dicoding.capspro.data.model.*
import com.dicoding.capspro.data.remote.forum.comment.Comment
import com.dicoding.capspro.data.remote.forum.comment.CommentResponse
import com.dicoding.capspro.data.remote.forum.report.ReportResponse
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.forum.thread.ThreadResponse
import com.dicoding.capspro.data.remote.forum.user.UserResponse
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
    suspend fun addUser(@Field("name") name: String, @Field("email") email: String)

    @GET("user/{email}")
    suspend fun getUser(@Path("email") email: String): UserResponse

    @GET("report/{email}")
    suspend fun getUserReport(@Path("email") email: String): ReportResponse

    @GET("thread")
    fun getThread(): Call<ThreadResponse>

    @PUT("thread/{id}/upvote")
    fun upvoteThread(@Path("id") threadId: String): Call<Thread>

    @FormUrlEncoded
    @POST("comment")
    fun addComment(
        @Field("threadId") threadId: String,
        @Field("email") email: String,
        @Field("comment") comment: String
    ): Call<Comment>

    @GET("comment/{threadId}")
    fun getComment(@Path("threadId") threadId: String): Call<CommentResponse>

    @DELETE("thread/{id}")
    suspend fun deleteThread(@Path("id") id: String)
}