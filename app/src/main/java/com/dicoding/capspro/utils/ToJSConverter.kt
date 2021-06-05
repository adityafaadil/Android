package com.dicoding.capspro.utils

class ToJSConverter {
    companion object {
        fun ArrayList<Int>.toJSArray(): String {
            var convertedString = "["
            for (i in 0..this.size - 1) {
                if (i == this.size - 1) {
                    convertedString += "${this[i]}]"
                    break
                }
                convertedString += "${this[i]},"
            }
            return convertedString
        }
    }
}