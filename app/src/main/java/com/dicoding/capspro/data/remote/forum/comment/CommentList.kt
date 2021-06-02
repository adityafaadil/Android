package com.dicoding.capspro.data.remote.forum.comment

import com.dicoding.capspro.data.remote.forum.user.User

data class CommentList(
    val user: User, val comment: Comment
)