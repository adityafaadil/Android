package com.dicoding.capspro.data.remote.forum.thread

data class ThreadResponse(
    val status: String,
    val message: String,
    val postedData: String,
    val data: ArrayList<ThreadList>
)
