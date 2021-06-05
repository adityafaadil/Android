package com.dicoding.capspro.data.remote.user

import java.io.Serializable

data class User(
    val _id: String,
    val profilePic: String,
    val name: String,
    val email: String,
    val registerDate: String
) : Serializable
