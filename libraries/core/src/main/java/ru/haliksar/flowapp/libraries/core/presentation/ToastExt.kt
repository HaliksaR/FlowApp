package ru.haliksar.flowapp.libraries.core.presentation

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) =
    toast(requireContext(), message, length)

fun Fragment.toast(@StringRes messageId: Int, length: Int = Toast.LENGTH_SHORT) =
    toast(requireContext(), null, messageId, length)

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) =
    toast(this, message, length)

fun Activity.toast(@StringRes messageId: Int, length: Int = Toast.LENGTH_SHORT) =
    toast(this, null, messageId, length)

fun toast(
    context: Context,
    message: String? = null,
    @StringRes messageId: Int? = null,
    length: Int = Toast.LENGTH_SHORT
): Toast? {
    val toast = message?.let { Toast.makeText(context, message, length) }
        ?: messageId?.let { Toast.makeText(context, messageId, length) }
        ?: return null
    toast.show()
    return toast
}
