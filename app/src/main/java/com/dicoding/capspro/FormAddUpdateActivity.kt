package com.dicoding.capspro

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.dicoding.capspro.data.DataForm
import com.dicoding.capspro.databinding.ActivityFormAddUpdateBinding
import com.dicoding.capspro.data.local.DatabaseContract
import com.dicoding.capspro.data.local.DatabaseContract.FormColumns.Companion.DATE
import com.dicoding.capspro.data.local.FormHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer

class FormAddUpdateActivity : AppCompatActivity(), View.OnClickListener {

    private var isEdit = false
    private var dataForm: DataForm? = null
    private var position: Int = 0
    private lateinit var formHelper: FormHelper

    val viewModel : MainViewModel by viewModels()

    private lateinit var binding: ActivityFormAddUpdateBinding

    companion object{
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

        binding = ActivityFormAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //form lokasi
        val edPlace = findViewById<AutoCompleteTextView>(R.id.edt_location)

        edPlace.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    viewModel.queryChannel.send(s.toString())
                }
            }
        })

//        viewModel.searchResult.observe(this, Observer { placesItem ->
//            val placesName = arrayListOf<String?>()
//            placesItem.map {
//                placesName.add(it.placeName)
//            }
//            val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, placesName)
//            adapter.notifyDataSetChanged()
//            edPlace.setAdapter(adapter)
//        })

        formHelper = FormHelper.getInstance(applicationContext)
        formHelper.open()
        dataForm = intent.getParcelableExtra(EXTRA_FORM)
        if (dataForm != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            dataForm = DataForm()
        }
        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"
            dataForm?.let {
                binding.edtName.setText(it.name)
                binding.edtAge.setText(it.age)
                binding.edtLocation.setText(it.location)
                binding.edtDescription.setText(it.description)
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Kirim laporan"
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSubmit.text = btnTitle

        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_submit) {
            val name = binding.edtName.text.toString().trim()
            val age = binding.edtAge.text.toString().trim()
            val location = binding.edtLocation.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()
            if (name.isEmpty()) {
                binding.edtName.error = "Field can not be blank"
                return
            }
            dataForm?.name = name
            dataForm?.age = age
            dataForm?.location = location
            dataForm?.description = description
            val intent = Intent()
            intent.putExtra(EXTRA_FORM, dataForm)
            intent.putExtra(EXTRA_POSITION, position)

            val values = ContentValues()
            values.put(DatabaseContract.FormColumns.NAME, name)
            values.put(DatabaseContract.FormColumns.AGE, age)
            values.put(DatabaseContract.FormColumns.LOCATION, location)
            values.put(DatabaseContract.FormColumns.DESCRIPTION, description)
            if (isEdit) {
                val result = formHelper.update(dataForm?.id.toString(), values).toLong()
                if (result > 0) {
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    Toast.makeText(this@FormAddUpdateActivity, "Gagal mengupdate data", Toast.LENGTH_SHORT).show()
                }
            } else {
                dataForm?.date = getCurrentDate()
                values.put(DATE, getCurrentDate())
                val result = formHelper.insert(values)
                if (result > 0) {
                    dataForm?.id = result.toInt()
                    setResult(RESULT_ADD, intent)
                    finish()
                } else {
                    Toast.makeText(this@FormAddUpdateActivity, "Gagal menambah data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
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
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?"
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
                        Toast.makeText(this@FormAddUpdateActivity, "Gagal menghapus data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}