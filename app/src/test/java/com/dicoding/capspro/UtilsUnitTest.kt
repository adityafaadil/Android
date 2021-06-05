package com.dicoding.capspro

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
        val arrayList = arrayListOf(1,2,3)
        val js = arrayList.toJSArray()
        assertEquals("[1,2,3]", js)
    }
}