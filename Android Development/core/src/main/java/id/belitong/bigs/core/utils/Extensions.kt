package id.belitong.bigs.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import id.belitong.bigs.core.R
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

fun ImageView.loadGeositeImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .apply(
            RequestOptions().override(500, 500)
//                .placeholder(R.drawable.ic_placeholder)
        )
        .into(this)
}

fun ImageView.loadUserImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .apply(
            RequestOptions().override(500, 500)
                .placeholder(R.drawable.ic_profile)
        )
        .into(this)
}

fun Int.meterToKilometer(): String {
    return if (this > 1000) {
        val distance = this / 1000
        "$distance km"
    } else {
        "$this m"
    }
}

fun String.getFirstName(): String = this.split(" ")[0]

fun String.getFirstTwoWords(): String = this.split(" ")[0] + " " + this.split(" ")[1]