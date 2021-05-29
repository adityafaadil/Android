package com.dicoding.capspro.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataForm (
    var id: Int = 0,
    var name: String? = null,
    var age: String? = null,
    var location: String? = null,
    var description: String? = null,
    var date: String? = null
) : Parcelable