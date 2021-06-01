package com.dicoding.capspro.data

import com.dicoding.capspro.data.remote.RemoteSource

class Repository (private val remoteSource: RemoteSource) {
    fun getThread() = remoteSource.getThread()
}