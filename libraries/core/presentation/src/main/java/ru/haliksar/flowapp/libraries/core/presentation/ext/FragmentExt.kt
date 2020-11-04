package ru.haliksar.flowapp.libraries.core.presentation.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    hostId: Int? = null
) {
    if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }.navigate(resId, args, navOptions)
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}
