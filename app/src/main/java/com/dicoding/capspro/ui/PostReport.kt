package com.dicoding.capspro.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capspro.R
import com.dicoding.capspro.data.DataForm
import com.dicoding.capspro.data.local.FormHelper
import com.dicoding.capspro.databinding.ActivityPostReportBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PostReport : AppCompatActivity() {

    private var isEdit = false
    private var dataForm: DataForm? = null
    private var position: Int = 0
    private lateinit var formHelper: FormHelper

    private lateinit var binding: ActivityPostReportBinding
    private val viewModel: PostingViewModel by viewModel()

    companion object {
        const val EXTRA_FORM = "extra_form"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

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
            val age = binding.postReportAge.editText!!.text.toString().toInt()
            val location =
                (binding.postReportLocation.editText as AutoCompleteTextView).text.toString()
            val content = binding.postReportContent.editText!!.text.toString()

            viewModel.addReport(TEST_USER_EMAIL, name, age, gender, location, content)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = "Batal"
            dialogMessage = "Apakah anda ingin membatalkan mengisi form?"
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?"
            dialogTitle = "Hapus laporan"
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton("Ya") { _, _ ->
                if (isDialogClose) {
                    finish()
                } else {
                    val result = formHelper.deleteById(dataForm?.id.toString()).toLong()
                    if (result > 0) {
                        val intent = Intent()
                        intent.putExtra(EXTRA_POSITION, position)
                        setResult(RESULT_DELETE, intent)
                        finish()
                    } else {
                        Toast.makeText(this@PostReport, "Gagal menghapus data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            .setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}