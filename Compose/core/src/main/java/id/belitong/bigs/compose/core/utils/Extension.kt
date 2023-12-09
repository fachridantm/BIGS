package id.belitong.bigs.compose.core.utils

import android.content.Context
import android.widget.Toast
import org.json.JSONObject
import retrofit2.HttpException

fun HttpException.getErrorMessage(): String? {
    val response = this.response()?.errorBody()?.string()
    if (response == null || response.isEmpty()) return "Something went wrong"
    return try {
        val jsonObject = JSONObject(response)
        jsonObject.getString("message")
    } catch (e: Exception) {
        e.printStackTrace()
        e.message
    }
}

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Int.meterToKilometer(): String {
    return if (this >= 1000) {
        val doubleValue = this.toDouble()
        val distance = doubleValue / 1000
        if (distance % 1 == 0.0) {
            "${distance.toInt()} km"
        } else {
            "$distance km"
        }
    } else {
        "$this m"
    }
}

fun String.getFirstName(): String = this.split(" ")[0]

fun String.getFirstTwoWords(): String = this.split(" ")[0] + " " + this.split(" ")[1]

fun String.orDash(): String = if (this.isEmpty()) "-" else this
