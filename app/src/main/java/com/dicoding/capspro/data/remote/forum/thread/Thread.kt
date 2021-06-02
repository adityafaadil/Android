package com.dicoding.capspro.data.remote.forum.thread

import java.io.Serializable

data class Thread(
    val _id: String,
    val content: String,
    val vote: Int,
    val numComment: Int,
    val date: String
) : Serializable
