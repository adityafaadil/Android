package com.dicoding.capspro.helper

import android.database.Cursor
import com.dicoding.capspro.data.DataForm
import com.dicoding.capspro.data.local.DatabaseContract

object MappingHelper {

    fun mapCursorToArrayList(formsCursor: Cursor?): ArrayList<DataForm> {
        val formsList = ArrayList<DataForm>()
        formsCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FormColumns._ID))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.FormColumns.NAME))
                val age = getString(getColumnIndexOrThrow(DatabaseContract.FormColumns.AGE))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.FormColumns.LOCATION))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.FormColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.FormColumns.DATE))
                formsList.add(DataForm(id, name, age, location, description, date))
            }
        }
        return formsList
    }
}