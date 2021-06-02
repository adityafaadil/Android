package com.dicoding.capspro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataPost (
    var postid: Int = 0,
    var titlepost: String? = null,
    var posting: String? = null
) : Parcelable