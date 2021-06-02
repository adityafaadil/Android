package com.dicoding.capspro.ui.forum

import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class ForumViewModel(private val repo: Repository) : ViewModel() {

    fun getThread() = repo.getThread()
}