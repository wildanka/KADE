package com.example.dan.kadesubmission2.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {
    fun rawStringToDateConverter(inputString : String): String{
        var inputFormat : DateFormat = SimpleDateFormat("yyyy-mm-dd")
        var outputFormat : DateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
        var date : Date = inputFormat.parse(inputString)
        var outputStr : String = outputFormat.format(date)

//        var formatTanggal = LocalDate.parse(inputString, DateTimeFormatter.ofPattern("EEE, dd MMM yyy", Locale.getDefault()))
//        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-d",Locale)

//        println(formatTanggal.toString())
        println("TANGGAL CONVERT"+outputStr)
        return outputStr
    }

//    fun date

    fun tambahGMT(inputString: String):String{
        var gmtPlus7 = inputString.toInt() + 7
        println("sementara $gmtPlus7")
        if (gmtPlus7 >= 24) gmtPlus7 -= 24
        println("setelahnya :  $gmtPlus7")
        return gmtPlus7.toString()
    }
}