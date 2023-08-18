package com.webage.lab09receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.webage.lab09receiver.ui.theme.Lab09ReceiverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(
            this,
            "com.webage.broadcast.notifications",
            "broadcasts",
            "Channel for broadcast notifications"
        )

        setContent {
            Lab09ReceiverTheme {
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


var notificationId = 0

@Composable
fun Main() {

    Text("Broadcast Receiver")

    val context = LocalContext.current
    val systemAction = BluetoothAdapter.ACTION_STATE_CHANGED

    DisposableEffect( context, systemAction ) {
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        val broadcastReceiver : BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                var bluetoothState = "UNKNOWN"

                when(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)) {
                    10 -> bluetoothState = "OFF"
                    11 -> bluetoothState = "TURNING ON"
                    12 -> bluetoothState = "ON"
                    13 -> bluetoothState = "TURNING OFF"
                }

                doNotify(context, "Bluetooth: ${bluetoothState}")
            }
        }

        context.registerReceiver(broadcastReceiver,intentFilter)

        onDispose {
            context.unregisterReceiver(broadcastReceiver)
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

fun doNotify( context: Context, message :String ) {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
        addNextIntent(intent)
        getPendingIntent(0,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    var notification = NotificationCompat.Builder(context, "com.webage.broadcast.notifications")
        .setSmallIcon(R.drawable.ic_smiley_face_icon)
        .setContentTitle("Broadcast Received")
        .setContentText("${notificationId}: ${message}")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with( NotificationManagerCompat.from(context)) {
        notify(notificationId, notification)
    }

    notificationId++
}