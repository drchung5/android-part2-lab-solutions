package com.webage.lab05intentreciver

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.webage.lab05intentreciver.ui.theme.Lab05IntentReciverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab05IntentReciverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val context = LocalContext.current
                    val activity = context.getActivity()
                    val extras = activity?.intent?.extras

                    if ( extras == null ) {
                        Greeting("Android")
                    } else {
                        val firstName = extras.get("first").toString()
                        val lastName  = extras.get("last").toString()
                        Greeting( "${firstName} ${lastName}")
                    }
                }
            }
        }
    }
}

// this extension function simplifies the process of getting the Activity
fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab05IntentReciverTheme {
        Greeting("Android")
    }
}