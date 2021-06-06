package com.dicoding.capspro

import com.dicoding.capspro.utils.TimeFormat.Companion.toLocaleDate
import com.dicoding.capspro.utils.TimeFormat.Companion.toTimeAgo
import com.dicoding.capspro.utils.ToJSConverter.Companion.toJSArray
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsUnitTest {
    @Test
    fun toJSArray_is_correct() {
        val arrayList = arrayListOf(1, 2, 3)
        val js = arrayList.toJSArray()
        assertEquals("[1,2,3]", js)
    }

    @Test
    fun GMT_Date_format_is_correct() {
        val dateString = "2021-05-19T05:10:47.985Z"
        assertEquals("Wed 19 May, 2021  12:10 PM", dateString.toLocaleDate())
    }

    @Test
    fun timeAgo_is_correct() {
        val dateString = "2021-05-19T05:10:47.985Z"
        val daysAgo = "18 days ago"
        assertEquals(daysAgo,dateString.toTimeAgo())
    }
}