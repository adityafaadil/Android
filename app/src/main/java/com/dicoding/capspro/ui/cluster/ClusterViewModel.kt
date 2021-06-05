package com.dicoding.capspro.ui.cluster

import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class ClusterViewModel(private val repo: Repository) : ViewModel() {
    fun getClusterData() = repo.getClusterData()
}