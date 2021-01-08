"# Testing-Notification-App"

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

FIRST COMMIT

first, i make a Networkutil In the first commit i use a notification builder and provide to it a title text, a content text and an icon. Once the builder has all necesarry fields i make a notify()

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

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

SECOND COMMIT

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

Third COMMIT

in this commit, I just add:
//TODO 3.0
Code that use notificationManager.sendNotification from NetworkUtil (this code shows a notification)

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

Four COMMIT
