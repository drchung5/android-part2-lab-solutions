package com.webage.lab06animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webage.lab06animation.ui.theme.Lab06AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab06AnimationTheme {
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
    val poem = """
            |Some say the world will end in fire,
            |  Some say in ice.
            |From what I’ve tasted of desire
            |  I hold with those who favor fire.
            |But if it had to perish twice,
            |  I think I know enough of hate
            |To say that for destruction ice
            |  Is also great
            |And would suffice.
            |
            |     Fire and Ice, Robert Frost
        """.trimMargin()

    var visible by remember { mutableStateOf(false) }
    var animate by remember { mutableStateOf(false )}

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(

        ){
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = { visible = !visible }
            ) {
                Text( if(visible) "Hide" else "Show" )
            }
            Button(
                onClick = { animate = !animate }
            ) {
                Text( "Toggle Animation" )
            }
        }

        AnimatedVisibility(visible) {
            Text(
                poem,
                modifier = Modifier
                    .graphicsLayer {
                        if(animate) {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center
                        } else {
                            // no animation
                        }
                    }
            )
        }

    }
}

