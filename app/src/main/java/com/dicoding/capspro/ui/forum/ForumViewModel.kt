package com.dicoding.capspro.ui.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class ForumViewModel(private val repo: Repository) : ViewModel() {

    fun getThread() = repo.getThread()
    private val _text = MutableLiveData<String>().apply {
        value = "This is forum Fragment"
    }
    val text: LiveData<String> = _text
}