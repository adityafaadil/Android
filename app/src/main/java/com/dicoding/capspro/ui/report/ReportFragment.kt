package com.dicoding.capspro.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capspro.databinding.FragmentReportBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportFragment : Fragment() {


    private lateinit var binding: FragmentReportBinding
    private val viewModel: ReportViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserReport(TEST_USER_EMAIL).observe(viewLifecycleOwner, {
            binding.reportForm.layoutManager = LinearLayoutManager(context)
            binding.reportForm.adapter = ReportAdapter(it)
            binding.reportLoading.visibility = View.GONE
            binding.reportForm.visibility = View.VISIBLE
        })

        /*binding.add.setOnClickListener {
            val intent = Intent(this, FormAddUpdateActivity::class.java)
            startActivityForResult(intent, FormAddUpdateActivity.REQUEST_ADD)
        }*/

    }
}