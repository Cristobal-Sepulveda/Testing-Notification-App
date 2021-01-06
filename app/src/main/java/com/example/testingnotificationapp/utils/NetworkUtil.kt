package com.example.testingnotificationapp.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
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
    // TODO: Step 1.11 create intent

    // TODO: Step 1.12 create PendingIntent

    // TODO: Step 2.0 add style

    // TODO: Step 2.2 add snooze action

    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    // Build the notification
    /** To support devices running older versions, you need to Use NotificationCompat.Builder instead
    of Notification.Builder */
    /** This String uses _channel_id because Starting with API lvl 26, all notification must
    be assigned to that channel*/
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )
    // TODO: Step 1.3 set title, text and icon to builder
    /** This is the minimum amount of data you need to set in order to send a notification */
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext
        .getString(R.string.notification_title))
        .setContentText(messageBody)

    // TODO: Step 1.4 call notify
    /** You need to call notify with an unique ID and the builder + .build() */
    /** We can call this function because we are in a function that allow us to do that*/
    notify(NOTIFICATION_ID, builder.build())

    // TODO: Step 1.8 use the new 'breakfast' notification channel



    // TODO: Step 1.13 set content intent

    // TODO: Step 2.1 add style to builder

    // TODO: Step 2.3 add snooze action

    // TODO: Step 2.5 set priority



}

// TODO: Step 1.14 Cancel all notifications