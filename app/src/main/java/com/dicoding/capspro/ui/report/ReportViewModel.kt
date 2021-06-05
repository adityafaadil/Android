package com.dicoding.capspro.ui.report

import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class ReportViewModel(private val repo: Repository) : ViewModel() {
    fun getUserReport(email: String) = repo.getUserReport(email)
}