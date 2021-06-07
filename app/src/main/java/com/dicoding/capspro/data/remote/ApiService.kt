package com.dicoding.capspro.data.remote

import com.dicoding.capspro.data.remote.cluster.ClusterResponse
import com.dicoding.capspro.data.remote.forum.comment.Comment
import com.dicoding.capspro.data.remote.forum.comment.CommentResponse
import com.dicoding.capspro.data.remote.report.Report
import com.dicoding.capspro.data.remote.report.ReportResponse
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.forum.thread.ThreadResponse
import com.dicoding.capspro.data.remote.user.User
import com.dicoding.capspro.data.remote.user.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("user")
    suspend fun addUser(
        @Field("profilePic") profilePic: String,
        @Field("name") name: String,
        @Field("email") email: String
    )

    @GET("user/{email}")
    suspend fun getUser(@Path("email") email: String): UserResponse

    @PUT("user/{id}")
    fun updateUser(@Path("id") id: String): Call<User>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Call<User>

    @FormUrlEncoded
    @POST("report")
    fun addReport(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("age") age: Int,
        @Field("gender") gender: String,
        @Field("location") location: String,
        @Field("content") content: String
    ): Call<ReportResponse>

    @GET("report/{email}")
    fun getUserReport(@Path("email") email: String): Call<ReportResponse>

    @FormUrlEncoded
    @POST("thread")
    fun addThread(
        @Field("email") email: String,
        @Field("threadTitle") threadTitle: String,
        @Field("content") content: String
    ): Call<ThreadResponse>

    @GET("thread")
    fun getThread(): Call<ThreadResponse>

    @PUT("thread/{id}/upvote")
    fun upvoteThread(@Path("id") threadId: String): Call<Thread>

    @DELETE("thread/{id}")
    fun deleteThread(@Path("id") id: String): Call<Thread>

    @FormUrlEncoded
    @POST("comment")
    fun addComment(
        @Field("threadId") threadId: String,
        @Field("email") email: String,
        @Field("comment") comment: String
    ): Call<Comment>

    @GET("comment/{threadId}")
    fun getComment(@Path("threadId") threadId: String): Call<CommentResponse>

    @DELETE("comment/{id}")
    fun deleteComment(@Path("id") id: String): Call<Comment>

    @GET("cluster")
    fun getClusterData():Call<ClusterResponse>
}