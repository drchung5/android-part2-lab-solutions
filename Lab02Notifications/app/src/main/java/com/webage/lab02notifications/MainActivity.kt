package com.webage.lab02notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.webage.lab02notifications.ui.theme.Lab02NotificationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(
            this,
            "com.webage.lab02notifications",
            "Channel 1",
            "Channel for NotificationApp"
        )

        setContent {
            Lab02NotificationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun MainPage() {

    val context = LocalContext.current

    var notificationId = 0

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text("Notification App")
        Button(
            onClick = {

                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                    addNextIntent(intent)
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }

                var notification = NotificationCompat.Builder(context, "com.webage.lab02notifications")
                    .setSmallIcon(R.drawable.ic_smiley_face_icon)
                    .setContentTitle("Notification App")
                    .setContentText("${notificationId}: This is important!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()

                with( NotificationManagerCompat.from(context)) {
                    notify(notificationId, notification)
                }

                notificationId++
            }
        ) {
            Text("Send Notification")
        }
    }
}

fun createNotificationChannel(
    context: Context,
    channelId :String,
    channelName :String,
    channelDescription :String) {

    // Create the NotificationChannel.
    val channel = NotificationChannel(
        channelId,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT,
    )

    channel.description = channelDescription

    // Register the channel with the system. You can't change the importance
    // or other notification behaviors after this.
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)

}