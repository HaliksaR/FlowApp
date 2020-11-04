package ru.haliksar.flowapp.navigation_graph

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.haliksar.flowapp.app.navigation_graph.R

fun AppCompatActivity.setBottomNavigationView(
    @IdRes navBottomBar: Int = R.id.navBottomBar,
    @IdRes navHost: Int = R.id.fragmentNavHost,
) {
    findViewById<BottomNavigationView>(navBottomBar)
        .setupWithNavController(Navigation.findNavController(this, navHost))
}