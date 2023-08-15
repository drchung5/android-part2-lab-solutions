package com.webage.lab05intentsender

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.webage.lab05intentsender.ui.theme.Lab05IntentSenderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab05IntentSenderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetName()
                }
            }
        }
    }
}

@Composable
fun GetName() {

    ConstraintLayout {
        val (first, last, submit, name, reset) = createRefs()

        var show by remember { mutableStateOf(false) }
        var firstname by remember { mutableStateOf("") }
        var lastname by remember { mutableStateOf("") }

        OutlinedTextField(
            enabled = !show,
            value = firstname,
            onValueChange = {firstname = it},
            label = {Text("First name")},
            modifier = Modifier
                .constrainAs(first) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(submit.start)
                }
                .size(250.dp, 65.dp)
        )
        OutlinedTextField(
            enabled = !show,
            value = lastname,
            onValueChange = {lastname = it},
            label = {Text("Last name")},
            modifier = Modifier
                .constrainAs(last) {
                    top.linkTo(first.bottom, 10.dp)
                    start.linkTo(first.start)
                    end.linkTo(first.end)
                }
                .size(250.dp, 65.dp)
        )

        val context = LocalContext.current

        Button(
            enabled = lastname != "" && firstname != "" && !show,
            onClick = {
                show = true
                val myIntent = Intent("ACTION_SHOWNAME").also {
                    it. putExtra("first", firstname)
                    it. putExtra("last", lastname)
                }
                with(context) { startActivity(myIntent) }
            },
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(submit) {
                    top.linkTo(parent.top, 13.dp)
                    start.linkTo(first.end)
                    end.linkTo(parent.end)
                }
                .height(133.dp)
        ) {
            Text( "Submit" )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab05IntentSenderTheme {
        GetName()
    }
}