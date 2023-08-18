package com.webage.lab10applicationstate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.webage.lab10applicationstate.ui.theme.Lab10ApplicationStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab10ApplicationStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main()
                }
            }
        }
    }
}

const val TAG = "MAIN"

@Composable
fun Main() {
    Text("Application State")

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val latestLifecycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            latestLifecycleEvent.value = event
            when (latestLifecycleEvent.value) {
                Lifecycle.Event.ON_START -> Log.wtf(TAG, Lifecycle.Event.ON_START.name)
                Lifecycle.Event.ON_CREATE -> Log.wtf(TAG, Lifecycle.Event.ON_CREATE.name)
                Lifecycle.Event.ON_RESUME -> Log.wtf(TAG, Lifecycle.Event.ON_RESUME.name)
                Lifecycle.Event.ON_PAUSE -> Log.wtf(TAG, Lifecycle.Event.ON_PAUSE.name)
                Lifecycle.Event.ON_STOP -> Log.wtf(TAG, Lifecycle.Event.ON_STOP.name)
                Lifecycle.Event.ON_DESTROY -> Log.wtf(TAG, Lifecycle.Event.ON_DESTROY.name)
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

