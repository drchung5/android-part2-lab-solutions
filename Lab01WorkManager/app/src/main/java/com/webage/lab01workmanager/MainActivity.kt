package com.webage.lab01workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.*
import com.webage.lab01workmanager.ui.theme.Lab01WorkManagerTheme
import java.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01WorkManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WorkManagerPage()
                }
            }
        }
    }
}

@Composable
fun WorkManagerPage() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "WorkManger App",
            Modifier.padding(10.dp)
        )

        Button(
            onClick = {

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val myWorkRequest: WorkRequest =
                    OneTimeWorkRequestBuilder<MyWorker>()
                        .setConstraints(constraints)
                        .build()

                WorkManager
                    .getInstance(context)
                    .enqueue(myWorkRequest)

            },
            modifier = Modifier.padding(4.dp)
        ) {
            Text("Enqueue Work")
        }

    }
}
