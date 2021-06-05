package com.dicoding.capspro.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.report.Report
import com.dicoding.capspro.databinding.ItemReportBinding

class ReportAdapter(private val listItem: ArrayList<Report>) :
    RecyclerView.Adapter<ReportAdapter.FormViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return FormViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReportBinding.bind(itemView)
        fun bind(report: Report) {
            binding.reportDate.text = report.date
            binding.reportName.text = report.name
            binding.reportAge.text = "( ${report.age} Tahun )"
            binding.reportContent.text = report.content
            binding.reportLocation.text = report.location
            if (report.gender == "L") {
                binding.reportGender.setImageResource(R.drawable.male)
            } else {
                binding.reportGender.setImageResource(R.drawable.female)
            }
        }
    }
}