package com.example.testingnotificationapp.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver
import com.example.testingnotificationapp.MainActivity
import com.example.testingnotificationapp.R

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    // Create the content intent for the notification, which launches
    // this activity
    // TODO: Step 5.1
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // TODO: Step 5.2
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    // TODO: Step 8.1
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )
    // TODO: Step 8.2
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    //TODO: Step 9.2
    val snoozeIntent = Intent (applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            REQUEST_CODE,
            snoozeIntent,
            FLAGS
    )

    // TODO: Step 1.1 Build the notification
    /** To support devices running older versions, you need to Use NotificationCompat.Builder instead
    of Notification.Builder */
    /** This String uses _channel_id because Starting with API lvl 26, all notification must
    be assigned to that channel*/

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext
        .getString(R.string.notification_title))
        .setContentText(messageBody)

        // TODO: Step 5.3
        .setContentIntent((contentPendingIntent))
        .setAutoCancel(true)
            // TODO: Step 8.3
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
    // TODO: Step 9.3
            .addAction(
                    R.drawable.egg_icon,
                    applicationContext.getString(R.string.snooze),
                    snoozePendingIntent
            )
    // TODO: Step 1.2
    /** You need to call notify with an unique ID and the builder + .build() */
    /** We can call this function because we are in a function that allow us to do that*/
    notify(NOTIFICATION_ID, builder.build())

    // TODO: Step 1.8 use the new 'breakfast' notification channel



    // TODO: Step 1.13 set content intent

    // TODO: Step 2.1 add style to builder

    // TODO: Step 2.3 add snooze action

    // TODO: Step 2.5 set priority



}

// TODO: Step 7.1
fun NotificationManager.cancelNotifications(){
    cancelAll()
}


