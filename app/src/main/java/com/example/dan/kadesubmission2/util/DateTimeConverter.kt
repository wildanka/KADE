package com.example.dan.kadesubmission2.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {
    @SuppressLint("SimpleDateFormat")
    fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
        SimpleDateFormat("EEE, dd MMM yyy").format(this)
    }

    fun dayConverter(dayInt : Int?) : String?{
        when (dayInt) {
            0 -> return "Sun"
            1 -> return "Mon"
            2 -> return "Tue"
            3 -> return "Wed"
            4 -> return "Thu"
            5 -> return "Fri"
            6 -> return "Sat"
        }
        return "what?"
    }

    fun monthConverter(monthInt : Int) : String?{
        when (monthInt) {
            0 -> return "Jan"
            1 -> return "Feb"
            2 -> return "Mar"
            3 -> return "Apr"
            4 -> return "May"
            5 -> return "Jun"
            6 -> return "Jul"
            7 -> return "Aug"
            8 -> return "Sep"
            9 -> return "Oct"
            10 -> return "Nov"
            11 -> return "Dec"
        }
        return "what?"
    }

    fun toGMTFormat(date: String?, time: String?): Date? {
        val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }
    //region

    fun toGMTFormatStrDate(date: String?, time: String?): Date? {
        val formatter = SimpleDateFormat("MM/dd/yy HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }
    fun toGMTFormatSearch(date: String?, time: String): Date? {
        val formatter = SimpleDateFormat("dd/mm/yy HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }
    //endregion

    fun toGMTFormatDateEvent(date: String?, time: String?): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }

    fun toDoubleDigit(angka : String) :String{
        if (angka.length==1){
            return "0$angka"
        }
        return angka
    }


}