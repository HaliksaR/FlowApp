package ru.haliksar.flowapp.navigation

import android.app.Activity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.haliksar.flowapp.app.navigation.R

fun BottomNavigationView.apply(activity: Activity) {
    setupWithNavController(Navigation.findNavController(activity, R.id.fragmentNavHost))
}