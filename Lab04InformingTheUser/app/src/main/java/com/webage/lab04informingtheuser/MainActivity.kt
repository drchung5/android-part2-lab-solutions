package com.webage.lab04informingtheuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webage.lab04informingtheuser.ui.theme.Lab04InformingTheUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab04InformingTheUserTheme {
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

@Composable
fun Main() {

    Column (
        modifier = Modifier.padding(8.dp)
    ) {

        val (snackbarVisible, setSnackbarVisible) = remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }

        Button(onClick = { if(!snackbarVisible) showDialog = true }) {
            Text("Show Dialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Informing the User") },
                text = { Text("Would you like to visit the Snackbar?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        setSnackbarVisible(true)
                    }) {
                        Text("Yes".uppercase())
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("No".uppercase())
                    }
                },
            )
        }

        if (snackbarVisible) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    Button(onClick = {
                        setSnackbarVisible(false)
                    }) {
                        Text("Dismiss".uppercase())
                    }
                }
            ) { Text(text = "You asked for it, this is a snackbar!") }
        }


    }

}

