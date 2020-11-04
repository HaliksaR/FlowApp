package ru.haliksar.flowapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowapp.navigation_graph.setBottomNavigationView

@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bottom_navigation)
        setBottomNavigationView()
    }
}