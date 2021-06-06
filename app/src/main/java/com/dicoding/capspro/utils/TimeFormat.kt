package com.dicoding.capspro.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeFormat {
    companion object {
        private fun parser(date: String): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.parse(date)!!
        }

        fun String.toLocaleDate(): String {
            val dateParser = parser(this)
            val date = SimpleDateFormat("E d MMM, yyyy").format(dateParser)
            val time = SimpleDateFormat("hh:mm a").format(dateParser)
            return "$date  $time"
        }

        fun String.toTimeAgo(): String {
            val inputTime = parser(this).time
            val SECOND_MILLIS = 1000
            val MINUTE_MILLIS = 60 * SECOND_MILLIS
            val HOUR_MILLIS = 60 * MINUTE_MILLIS
            val DAY_MILLIS = 24 * HOUR_MILLIS

            var time = inputTime
            if (time < 1000000000000L) time *= 1000

            val now: Long = System.currentTimeMillis()
            if (time > now || time <= 0) {
                return "Invalid"
            }

            val diff: Long = now - time
            if (diff < MINUTE_MILLIS) {
                return "just now"
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago"
            } else if (diff < 50 * MINUTE_MILLIS) {
                return "${diff / MINUTE_MILLIS} minutes ago"
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago"
            } else if (diff < 24 * HOUR_MILLIS) {
                return "${diff / HOUR_MILLIS} hours ago"
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday"
            }
            return "${diff / DAY_MILLIS} days ago"
        }
    }
}