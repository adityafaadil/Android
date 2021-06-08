package com.dicoding.capspro.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capspro.databinding.ActivityPostThreadBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import com.dicoding.capspro.utils.CustomAlertDialog
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostThread : AppCompatActivity() {
    private lateinit var binding: ActivityPostThreadBinding
    private val viewModel: PostingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val snackbar = makeSnackbar(
            "Memeriksa postingan...",
            Snackbar.LENGTH_INDEFINITE
        )

        binding.postThreadSubmit.setOnClickListener {
            val threadTitle = binding.postThreadJudul.editText!!.text.toString()
            val content = binding.postThreadContent.editText!!.text.toString()
            if (threadTitle == "" || content == "") {
                if (threadTitle == "") {
                    binding.postThreadJudul.editText!!.error = "Tidak boleh kosong"
                }
                if (content == "") {
                    binding.postThreadContent.editText!!.error = "Tidak boleh kosong"
                }
            } else {
                binding.postThreadJudul.editText!!.isEnabled = false
                binding.postThreadContent.editText!!.isEnabled = false
                snackbar.show()
                viewModel.addThread(TEST_USER_EMAIL, threadTitle, content).observe(this, {
                    binding.postThreadJudul.editText!!.isEnabled = true
                    binding.postThreadContent.editText!!.isEnabled = true
                    snackbar.dismiss()
                    if (it as Boolean) {
                        makeSnackbar(
                            "Postingan anda mengandung unsur negatif...",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        finish()
                    }
                })
            }
        }
    }

    override fun onBackPressed() {
        CustomAlertDialog(this).showDialog("Batal", "Apakah anda ingin batal memposting thread?")
    }

    private fun makeSnackbar(text: String, length: Int): Snackbar {
        return Snackbar.make(
            binding.postThreadLayout,
            text,
            length
        )
    }
}