package com.dicoding.capspro.data.local

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class FormColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "form"
            const val _ID = "_id"
            const val NAME = "name"
            const val AGE = "age"
            const val LOCATION = "location"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }

}