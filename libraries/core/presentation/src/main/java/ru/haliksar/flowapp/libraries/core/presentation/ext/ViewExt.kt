package ru.haliksar.flowapp.libraries.core.presentation.ext

import android.view.View

fun View.isVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}