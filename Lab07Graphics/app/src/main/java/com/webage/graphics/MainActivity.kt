package com.webage.graphics

import android.os.Bundle
import androidx.compose.foundation.Canvas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import com.webage.graphics.ui.theme.Grapics1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Grapics1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Drawing()
                }
            }
        }
    }
}



@Composable
fun Drawing() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val points = listOf(
            Offset (150f, 575f), // 25
            Offset (250f, 550f), // 50
            Offset (350f, 500f), // 100
            Offset (450f, 375f), // 225
            Offset (550f, 225f), // 375
            Offset (650f, 150f), // 450
            Offset (750f, 175f), // 425
            Offset (850f, 275f), // 325
            Offset (950f, 500f), // 100
            Offset (1050f, 550f) // 50
        )


        for ( i in 0..11 ) {
            drawLine(
                Color.LightGray,
                Offset(100f*i+50f,50f),
                Offset(100f*i+50f, 600f),
                strokeWidth = 5f
            )
        }

        drawLine(
            Color.Black,
            Offset(50f, 600f),
            Offset(1150f, 600f),
            strokeWidth = 5f
        )

        drawPoints(
            points,
            PointMode.Points,
            Color.Red,
            strokeWidth = 30.0f,
            cap = StrokeCap.Round
        )

//        drawCircle( Color.Red )
//
//        drawCircle(
//            Color.Blue,
//            radius = size.minDimension/3.3f,
//            alpha = 0.5f
//        )
//
//        drawCircle(
//            Color.Blue,
//            radius = size.minDimension/8.0f,
//            alpha = 1f
//        )


//        drawLine(
//            Color.Red,
//            Offset(50f,50f),
//            Offset(400f, 600f),
//            strokeWidth = 25f
//        )
//
//        drawLine(
//            Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
//            Offset(500f,300f),
//            Offset(1000f, 300f),
//            strokeWidth = 100f,
//            cap = StrokeCap.Round,
//        )
    }
}

//    Text( "Hello, Graphics!",
//        modifier = Modifier
//            .fillMaxSize()
//            .drawBehind {
//                drawRect(
//                    color = Color.LightGray,
//                    size = Size( 50f, 300f )
//                )
//            }
//    )

//}

