package com.dicoding.capspro.data.remote.forum.comment

data class CommentResponse(
    val status: String,
    val message: String,
    val postedData:Comment,
    val data: ArrayList<CommentList>
)