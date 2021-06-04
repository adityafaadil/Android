package com.dicoding.capspro.ui.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capspro.FormAddUpdateActivity
import com.dicoding.capspro.adapter.FormAdapter
import com.dicoding.capspro.data.DataForm
import com.dicoding.capspro.databinding.ActivityReportBinding
import com.dicoding.capspro.data.local.FormHelper
import com.dicoding.capspro.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ReportActivity : AppCompatActivity() {

    private lateinit var adapter: FormAdapter

    private lateinit var binding: ActivityReportBinding

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Riwayat laporan"

        binding.rvForms.layoutManager = LinearLayoutManager(this)
        binding.rvForms.setHasFixedSize(true)
        adapter = FormAdapter(this)
        binding.rvForms.adapter = adapter

        /*binding.add.setOnClickListener {
            val intent = Intent(this, FormAddUpdateActivity::class.java)
            startActivityForResult(intent, FormAddUpdateActivity.REQUEST_ADD)
        }*/

        if (savedInstanceState == null) {
            loadFormsAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<DataForm>(EXTRA_STATE)
            if (list != null) {
                adapter.listForms = list
            }
        }
    }

    private fun loadFormsAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressbar.visibility = View.VISIBLE
            val formHelper = FormHelper.getInstance(applicationContext)
            formHelper.open()
            val deferredForms = async(Dispatchers.IO) {
                val cursor = formHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }

            binding.progressbar.visibility = View.INVISIBLE
            val forms = deferredForms.await()
            if (forms.size > 0) {
                adapter.listForms = forms
            } else {
                adapter.listForms = ArrayList()
                showSnackbarMessage("Tidak ada laporan saat ini")
            }
            formHelper.close()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listForms)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            when (requestCode) {
                FormAddUpdateActivity.REQUEST_ADD -> if (resultCode == FormAddUpdateActivity.RESULT_ADD) {
                    val dataForm = data.getParcelableExtra<DataForm>(FormAddUpdateActivity.EXTRA_FORM) as DataForm

                    adapter.addItem(dataForm)
                    binding.rvForms.smoothScrollToPosition(adapter.itemCount - 1)

                    showSnackbarMessage("Satu item berhasil ditambahkan")
                }
                FormAddUpdateActivity.REQUEST_UPDATE ->
                    when (resultCode) {
                        FormAddUpdateActivity.RESULT_UPDATE -> {

                            val dataForm = data.getParcelableExtra<DataForm>(FormAddUpdateActivity.EXTRA_FORM) as DataForm
                            val position = data.getIntExtra(FormAddUpdateActivity.EXTRA_POSITION, 0)

                            adapter.updateItem(position, dataForm)
                            binding.rvForms.smoothScrollToPosition(position)

                            showSnackbarMessage("Satu item berhasil diubah")
                        }

                        FormAddUpdateActivity.RESULT_DELETE -> {
                            val position = data.getIntExtra(FormAddUpdateActivity.EXTRA_POSITION, 0)

                            adapter.removeItem(position)

                            showSnackbarMessage("Satu item berhasil dihapus")
                        }
                    }
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvForms, message, Snackbar.LENGTH_SHORT).show()
    }
}