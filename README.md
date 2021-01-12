"# Testing-Notification-App"

Here I'll practice how to create, send,
update, cancel, and customize notifications.
I've seen how to use notifications effectively by adding
actions using page outs and different styles depending on the needs.
I also learned different importance levels and how to
set or change the importance with respect to the users.

I also learn how to use push notifications with Firebase Cloud Messaging.
I created a new Firebase project and edit the Firebase project to this Android project.
I've seen how to send the Firebase Cloud message and also use
ICM to transfer data between the backend and the mobile app.

Also, I worked on a fully functional existing app,
but make it more awesome by adding notifications
when necessary without disturbing the users.

FIRST COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
first, i make a Networkutil
//TODO 1.1
I use a notification builder and provide to it a title text, a content text and an icon. Once the builder has all necesarry fields
//TODO 1.2
then i make a notify()
// TODO 1.3
then, in MainViewModel i make a notificationManager:
            /** the argument NotificationManager is a systemService which provides all the
             * functions exposed for notifications API including the extension function you edit
             *
             * Any time you want to send, cancel or update a update a notification, you need to
             * request an instance of the Notification Manager from the system*/
            val notificationManager = ContextCompat.getSystemService(
                app, NotificationManager::class.java) as NotificationManager
            /** here we are calling the extension function sendNotification with the string, and
             * the context*/
            notificationManager.sendNotification(app.getString(R.string.timer_running), app)

SECOND COMMIT            
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
here i create the channel & customize the notification settings and behavior.
because i used egg_notification_channel_id as the notification channel, i start with create the channel
//TODO 2.1
first, i create a private function in mainFragment
//TODO 2.2
because channels are avaible from API LVL 26 and above, the version check is required.
notification channel is the constructor
the constructor needs an ID, a name (this name is seen by the user in app info) & the importance lvl for the channel.
//TODO 2.3
then, on the notification channel, we set enableLights, lightColor, enableVibration & description
//TODO 2.4
then we get an instance of notificationManager by calling getSystemService
//TODO 2.5
call createNotificationChannel on notificatiionManager instance and pass the notificationChannel object
//TODO 2.6
Now, to create a channel, I call the Function that I just create on mainFragment, in onCreateView, and I pass the arguments (the id is the same id that I put in the builder in NetworkUtil) /** Setting a wrong value as the channel id will make the notification fail
and the notification is done!
by using channels, i can customize the settings, and behavior for all notifications sent on this channel & the users can also change the settings for each channel by using the channel list
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

THIRD COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
in this commit, I just add:
//TODO 3.0
Code that use notificationManager.sendNotification from NetworkUtil (this code shows a notification)
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

FOUR COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Navigation Added
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

FIFTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
here, I will add a Content Intent:
//TODO 5.1
first, I create an Intent with the application context and the activity to be launched
//TODO 5.2
next, in NetworkUtil, I create a new PendingIntent. The system will use the PendingIntent to open the app if is closed
The PendingIntent flag specifies the option to create a new PendingIntent or use an existing one. I use FLAG_UPDATE_CURRENT as the flag because i dont want to create a new notification, but update if there's an existing one.
//TODO 5.3
next, I pass the PendingIntent to the notification (IN BUILDER) -> .setContentIntent(contentPendingIntent).
also, setAutoCancel(true), so that when the user tap the notification, the notification dismisses itself as it takes u to the app.
NOW, when the Notificacion is clicked, the PendingIntent will be triggered opening up the mainActivity
//TODO 6.1
here i use a switch.material from fragment_main to display a notification when .isChecked = true
val notificationManager = ContextCompat.getSystemService(
    requireContext(),
    NotificationManager::class.java
) as NotificationManager
binding.onOffSwitch.setOnClickListener{ v:View ->
 val isChecked = binding.onOffSwitch.isChecked
    if(isChecked){
        notificationManager.sendNotification(
            R.string.eggs_ready.toString(),
            requireContext())
    }
}
//TODO 7.1
update & cancel notifications.
first I start by changing the name of NetworkUtil to Notification Util and then make a new extension functions
fun NotificationManager.cancelNotifications() which calls cancelAll()
//TODO 7.2
I add the new extension function in the setOnCLickListener, this function is used when the switch is passed to unswitch
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

SIXTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
in this Step I will use notificationCompact.BigPictureStyle to create an expandable notification
//TODO 8.1
start by loading in notificationManager.sendNotification an Image from Resources by using the BitmapFactory
//TODO 8.2
After that, I create a new BigPictureStyle and set your image = val bigPicStyle = NotificatonCOmpact.BigPictureStyle
.bigPicture(//TODO 8.1).bigLargeIcon(null)
//TODO 8.3
set the new style to the builder by calling .setStyle(//TODO 8.2)
and . setLargeIcon(//TODO 8.1)
The notification is expanded automatically when is in the top of the notification, else, its collapsed.
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

SEVENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
in this commit I clean the git & rename all commits
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

EIGHTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Here I will add a notification action & use SnoozeReceiver to receive the user click
on this action
when this action is clicked, the SnoozeReceiver will receive an intent, and will
create a new alarm to send a notification after a minute
//TODO 9.1
in SnoozeReceiver.kt get an instance of notificationManager From system, and call
cancelAll
//TODO 9.2
in order to use SnoozeReceiver, go to NotificationUtils and create a new Intent,
just after the bigPicStyle. Also create a PendingIntent which will be used by
the system to set up a new alarm to post a notification after a minute when the
snooze button is tapped by the user(the context is which the pendingintent shall start the activity,
the request code is the request code for this pending intent, if you need to update
or cancel this PendingIntent, you need to use this code to access the PendingIntent, next
the intent is the intent of the activity to be launched. flag is the time that
the intent will be used
//TODO 9.3
Next, still on NetworkUtil, call the addAction function on the notification builder
.addAction(R.drawable .egg_icon, applicatioNContext.getString(R.string.snooze),
snoozePendingIntent) -> this intent that you create in todo 9.2 will be use to
trigger the right broadcast receiver when your action is clicked
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

NINETH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
This commit is about NotificationManager.Importance....
if you change the importance lvl in the createChanel, you will have diferents
behaviors in the app. see more details in net.
//TODO 10.1
to be ensure that the behavior you set work on devices with API LVL below 25, I
put in the builder of the notification .setPriority(NotificationCompat.PRIORITY_...)
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

TENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
This commit is about Notification Badges that are small dots that appears on
the launcher icon of the associated app when the app has an active notification.
  Users can long press on the app icon to reveal the notifications.
      This badgeds are setting by default, so i dont do nothing to able this,
      but, If some developer want to disable it, the only thing is the Next
//TODO 11.1
IN the function to create the channel createChanel write:
      .apply{
        setShowBadge(false)
    }
Remember to reinstall the app to see this
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

ELEVENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Step 1: Create a Firebase project
Before you can add Firebase to your Android app, you need to create a Firebase project to connect to your Android app.
After logging in to the Firebase console, click Add project, then select or enter a Project name. Name your project ‘fcm-codelab’.
Click Continue.
You can skip setting up Google Analytics and choose the ‘Not Right Now’ option.
Click ‘Create Project’ to finish setting up the Firebase project.
Step 2: Register your app with Firebase
Now that you have a Firebase project, you can add your Android app to it.
In the center of the Firebase console's project overview page, click the Android icon to launch the setup workflow.
Enter the package id of your android project, com.example.android.eggtimernotifications, as the Firebase application Id. Make sure you enter the ID your app is using because you cannot add or modify this value after you’ve registered your app with your Firebase project.
Click Register app.
Step 3: Add the Firebase configuration file to your project
Add the Firebase Android configuration file to your app:
Click Download google-services.json to obtain your Firebase Android config file (google-services.json).
You can download your Firebase Android config file again at any time.
Make sure the config file is not appended with additional characters and should only be named google-services.json
Move your config file into the module (app-level) directory of your app.
//TODO 12.1
Step 4: Configure your Android project to enable Firebase products
To enable Firebase products in your app, add the google-services plugin to your Gradle files.
//TODO 12.2
In your (project-level) Gradle file (build.gradle), add rules to include the Google Services plugin. Check that you have Google's Maven repository, as well.
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

TWELFTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Exercise: Single Device Notifications
//TODO 13.1
in app:gradle, add the dependencies to use FCM
//TODO 13.2
ADD THIS CODE TO THE MANIFEST: //this will enable FirebaseMessagingService
the service metadata in Android manifest registers .MyFirebaseMessagingService as
a service and enter an intent filter, so this service will receive messages sent
from FCM
& the last part of the metadata declares breakfast_notification_channel_id as default
notification channel id for firebase
Remember, its a good idea to create a new channel for FCM since your users may want
to enable or disable push notification separately
<service
               android:name=".MyFirebaseMessagingService"
               android:exported="false">
           <intent-filter>
               <action android:name="com.google.firebase.MESSAGING_EVENT"/>
           </intent-filter>
       </service>
       <!-- [START fcm_default_icon] -->
       <!--
Set custom default icon. This is used when no icon is set for incoming notification messages.
            See README(https://goo.gl/l4GJaQ) for more.
       -->
       <meta-data
               android:name="com.google.firebase.messaging.default_notification_icon"
               android:resource="@drawable/common_google_signin_btn_icon_dark"/>
       <!--
Set color used with incoming notification messages. This is used when no color is set for the incoming
            notification message. See README(https://goo.gl/6BKBk7) for more.
       -->
       <meta-data
               android:name="com.google.firebase.messaging.default_notification_color"
               android:resource="@color/colorAccent"/> <!-- [END fcm_default_icon] -->
       <!-- [START fcm_default_channel] -->
       <meta-data
           android:name="com.google.firebase.messaging.default_notification_channel_id"
           android:value="@string/breakfast_notification_channel_id" />
       <!-- [END fcm_default_channel] -->
//TODO 13.3
in MainFragment & in onCreateView, create a new channel with breakfast_notification_channel_id
and the breakfast_notification_channel_name from string resources.
//TODO 13.4
//add onNewToken: this code will print a registration token to locate everytime
a new token is received
in most cases, you may want to start this token.
onNewToken, as his name saids, will be called when a new token is generated.
this function is called only when a new token is created ( 1 per device & app, if
you reinstall the app, you will recive a new token)
//TODO 13.5 this TODO IS TO DO IN FIREBASE Console.
in order to send a notification, we will use the notification composer.
select Interaccion->Cloud Messaging->Send your first message|
here enter the notificaiton title & text, then, send test message
copy your app token from logCat by filtering  Refreshed Token
add and select the token (+). put the app in the background a press TEST
after you click test,  the targeted client device with the app in background should
receive the notification
TO SEE HOW WORK THIS IN FOREGROUND, see the next commit's
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

THIRTEENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Sending FCM Notifications to a topic
  if some topic messaging is based on publish/suscribe model, publish /suscribe
  allows backend apps to push relevant content to intereseted clients.
  topics, allows you to send a message to multiple devices that have opted into
  that particular topic
  -for clients, topics are specific data sources which the client is intereseted in
  -for the server, topics are groups of devices which are opted into receive updates
  on the specific data source
    topics, can be used for porpouses like suscribing news, weather forecast or
    sport results
  in this commit ill create a topic to remind the intereseted app users to something
    -to suscribe a topic, the client app calls, suscribe to topic on firebase
    messaging object need the SCM topic name
    -this goal can have two outcomes, if the caller succeeds, onCOmpleteListener
    callback will be called with suscribe message
    -if the client fails to suscribe, the same callback will receive an error
    message
in this commit, i will automatically suscribe the user to a topic, although in
most cases, you may want to leave the option of which topics to suscribe to, to
the user.
//TODO 14.1
open MainFragment and create the function subscribeTopic. you need to call THIS
function to subscribe to a topic when the app starts.
//TODO 14.2
to do that in onCreateView call subscribeTopic()
//TODO 14.3
this TODO is maked from firebase console. we can test sending messages to a topic.
open the notification composer (cloud messaging) and select send your first message.
write the title and the content and then, this time, instead of sending the
 message to a single device, click target>topic
and enter the name of the topic!
REMEMBER THAT ALL THIS MESSAGES WORK IS THE APP IS IN THE BACKGROUND!!!
THAT IS ALL!
now, the app have more that one channel to notifications now
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

FOURTEENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
for the porpouses of this commit, i want to remind the app user to have some
eggs for breakfast(notification payload), but, because we want to show the message
whether the app is in background or foreground, therefore ill send data payload too
when you send the fcm message to devices, the notification message will shown
automatically if the app is not running or in the background
however, if the app is running and in the foreground, then the notification is
not shown automatically and instead the app code decides what to do with the
message.
if the app is in the foreground when it receives an FCM message, the onMessageReceived
function will be triggered automatically with a RemoteMessage object
this is where your app can silently handle notification & data payload or trigger
the notification.
You dont need to send data with FCM to your app. but let's test this by printing
the data sent to the log.
in order to handle FCM messages, you need to handle the data payload into
onMessageReceived function of MyFirebaseMessagingService
the rules to send this messages are the same, but, in notificacion you must put
title ad content, then in segmentacion, tema del mensaje and then in otras opciones
put the key and value of the data
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

FIFTEENTH COMMIT
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
This commit is about Foreground vs BACKGROUND:
You want to make sure the user sees a notification popping up as a reminder.
lets make some code to trigger a notification:
on onMessageReceived in MyFirebaseMessagingService, right after the code you
recently entered to check the data message, I add code to reach send the
notification using the notifications framework.
//TODO 16.1
with this, you will see a notification even if the user is in the foreground
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
