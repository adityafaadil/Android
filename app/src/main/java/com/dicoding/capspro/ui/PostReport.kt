package com.dicoding.capspro.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capspro.R
import com.dicoding.capspro.databinding.ActivityPostReportBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import com.dicoding.capspro.utils.CustomAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostReport : AppCompatActivity() {

    private lateinit var binding: ActivityPostReportBinding
    private val viewModel: PostingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gender = "L"
        binding.postReportGender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.postReportGender_L -> gender = "L"
                R.id.postReportGender_P -> gender = "P"
            }
        }

        val dropdownAdapter = ArrayAdapter(
            this,
            R.layout.item_dropdown,
            resources.getStringArray(R.array.region_list)
        )
        (binding.postReportLocation.editText as AutoCompleteTextView).setAdapter(dropdownAdapter)

        binding.postReportSubmit.setOnClickListener {
            val name = binding.postReportName.editText!!.text.toString()
            val age = binding.postReportAge.editText!!.text.toString()
            val location =
                (binding.postReportLocation.editText as AutoCompleteTextView).text.toString()
            val content = binding.postReportContent.editText!!.text.toString()
            if (name == "" || age == "" || content == "") {
                if (name == "") {
                    binding.postReportName.editText!!.error = "tidak boleh kosong"
                }
                if (age == "") {
                    binding.postReportAge.editText!!.error = "tidak boleh kosong"
                }
                if (content == "") {
                    binding.postReportContent.editText!!.error = "tidak boleh kosong"
                }
            } else {
                viewModel.addReport(TEST_USER_EMAIL, name, age.toInt(), gender, location, content)
                finish()
            }
        }

    }

    override fun onBackPressed() {
        CustomAlertDialog(this).showDialog("Batal", "Apakah anda ingin batalkan mengisi form?")
    }
}