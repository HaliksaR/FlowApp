package ru.haliksar.flowapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow
import ru.haliksar.flowapp.libraries.network.wrappers.toNetworkException


@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    private val retrofit = createRetrofitService<Api>(get())

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            launch {
                safeCallFlow { retrofit.getFlow() }
                    .collect {
                        when (it) {
                            NetworkResponse.Loading -> Log.d("retrofit", "Loading")
                            is NetworkResponse.Success -> Log.d("retrofit", "Success ${it.data}")
                            is NetworkResponse.Error -> Log.d(
                                "retrofit",
                                "Error ${it.exception.code}"
                            )
                        }
                    }
            }
        }
    }

    private fun getDataRx() {
        retrofit.getRx()
            .subscribe({

            }, {
                Log.d("Observer", "onError ${it.toNetworkException().code}")
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flow.setOnClickListener {
            runBlocking {
                getData()
            }
        }
        rx.setOnClickListener {
            getDataRx()
        }
    }
}