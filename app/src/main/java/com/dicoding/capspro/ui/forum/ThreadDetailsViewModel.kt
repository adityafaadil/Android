package com.dicoding.capspro.ui.forum

import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class ThreadDetailsViewModel(private val repo: Repository) : ViewModel() {
    fun upvoteThread(threadId: String) = repo.upvoteThread(threadId)
    fun addComment(threadId: String, email: String, comment: String) =
        repo.addComment(threadId, email, comment)

    fun getComment(threadId: String) = repo.getComment(threadId)
}