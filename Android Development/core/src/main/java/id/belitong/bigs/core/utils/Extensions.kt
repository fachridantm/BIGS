package id.belitong.bigs.core.utils

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import retrofit2.HttpException

fun TextInputLayout.showError(isError: Boolean, message: String? = null) {
    if (isError) {
        isErrorEnabled = false
        error = null
        isErrorEnabled = true
        error = message
    } else {
        isErrorEnabled = false
        error = null
    }
}

fun String.showMessage(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

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