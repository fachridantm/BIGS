package id.belitong.bigs.core.utils

import android.content.Context
import android.widget.Toast
import org.json.JSONObject
import retrofit2.HttpException

fun HttpException.getErrorMessage(): String? {
    val response = this.response()?.errorBody()?.string()
    if (response == null || response.isEmpty()) return "Terjadi Kesalahan"
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