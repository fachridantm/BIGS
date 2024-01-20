package id.belitong.bigs.core.utils

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import id.belitong.bigs.core.R
import id.belitong.bigs.core.di.GlideApp
import id.belitong.bigs.core.di.GlideOptions
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

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun String.showSnackbar(
    view: View,
    anchorView: View? = null,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(view, this, duration).apply {
        if (anchorView != null) this.anchorView = anchorView
        show()
    }
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

fun ImageView.loadGeoparkImage(url: String) {
    GlideApp.with(this.context)
        .load(url)
        .apply(
            GlideOptions().override(500, 500)
                .placeholder(R.drawable.img_placeholder_geopark)
        )
        .into(this)
}

fun ImageView.loadUserImage(url: String) {
    GlideApp.with(this.context)
        .load(url)
        .apply(
            GlideOptions().override(500, 500)
                .placeholder(R.drawable.ic_profile)
        )
        .into(this)
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

fun TextView.statusBackgroundFilter(status: String) {
    when (status) {
        HistoryStatus.CONFIRMED.value, HistoryStatus.COMPLETED.value -> {
            this.backgroundTintList = ContextCompat.getColorStateList(
                this.context,
                R.color.md_theme_light_primary
            )
            this.text = status
        }

        HistoryStatus.REJECTED.value, HistoryStatus.CANCELLED.value -> {
            this.backgroundTintList = ContextCompat.getColorStateList(
                this.context,
                R.color.md_theme_light_error
            )
            this.text = status
        }

        else -> {
            this.text = status
        }
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> @Suppress("DEPRECATION") getParcelableExtra(key)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> @Suppress("DEPRECATION") getParcelable(key)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

inline fun <reified T : Parcelable> Intent.parcelableArrayList(key: String): ArrayList<T>? =
    when {
        SDK_INT >= 33 -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
        else -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
    }

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}