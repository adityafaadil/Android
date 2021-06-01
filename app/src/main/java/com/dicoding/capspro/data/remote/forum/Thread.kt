package com.dicoding.capspro.data.remote.forum

data class Thread(
    val _id :String,
    val content: String,
    val vote:Int,
    val numComment:Int,
    val date:String
)
