package com.dicoding.capspro.data.remote.forum.comment

data class Comment(
    val _id: String,
    val threadId: String,
    val email: String,
    val comment: String,
    val date: String
)
