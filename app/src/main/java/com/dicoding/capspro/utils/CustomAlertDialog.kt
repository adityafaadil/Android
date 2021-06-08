package com.dicoding.capspro.utils

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

class CustomAlertDialog(private val context: Context) {
    fun showDialog(dialogTitle: String, dialogMessage: String) {
        buildDialog(dialogTitle, dialogMessage).show()
    }

    private fun buildDialog(dialogTitle: String, dialogMessage: String): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton("Ya") { _, _ ->
                (context as Activity).finish()
            }
            .setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        return alertDialogBuilder.create()
    }
}