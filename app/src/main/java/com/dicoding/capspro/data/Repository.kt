package com.dicoding.capspro.data

import com.dicoding.capspro.data.remote.RemoteSource

class Repository(private val remoteSource: RemoteSource) {
    fun getUserReport(email: String) = remoteSource.getUserReport(email)

    fun getThread() = remoteSource.getThread()
    fun upvoteThread(threadId: String) = remoteSource.upvoteThread(threadId)
    fun addComment(threadId: String, email: String, comment: String) =
        remoteSource.addComment(threadId, email, comment)

    fun getComment(threadId: String) = remoteSource.getComment(threadId)
    fun getClusterData() = remoteSource.getClusterData()
}