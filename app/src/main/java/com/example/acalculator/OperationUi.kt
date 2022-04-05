package com.example.acalculator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
class OperationUi(val expression : String, val result: String, val timeStamp : Long) : Parcelable {
    val sdf = SimpleDateFormat("MM/dd/yyyy - hh:mm:ss")

    fun getTime() : String{
        return ""+sdf.format(timeStamp)
    }

    override fun toString(): String {
        return "${expression}=${result}"
    }
}