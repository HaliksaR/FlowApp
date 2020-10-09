package ru.haliksar.flowapp.libraries.core.presentation.ext

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snack(message: String, length: Int = Snackbar.LENGTH_LONG) =
    snack(
        requireView(),
        message,
        length
    )

fun Fragment.snack(@StringRes messageId: Int, length: Int = Snackbar.LENGTH_LONG) =
    snack(
        requireView(),
        null,
        messageId,
        length
    )

fun snack(
    view: View,
    message: String? = null,
    @StringRes messageId: Int? = null,
    length: Int = Snackbar.LENGTH_LONG
): Snackbar? {
    val snack = message?.let { Snackbar.make(view, message, length) }
        ?: messageId?.let { Snackbar.make(view, messageId, length) }
        ?: return null
    snack.show()
    return snack
}