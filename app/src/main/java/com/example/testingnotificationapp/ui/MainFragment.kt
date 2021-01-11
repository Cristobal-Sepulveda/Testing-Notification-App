    package com.example.testingnotificationapp.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.testingnotificationapp.R
import com.example.testingnotificationapp.databinding.FragmentMainBinding
import com.example.testingnotificationapp.utils.cancelNotifications
import com.example.testingnotificationapp.utils.sendNotification
import com.google.firebase.messaging.FirebaseMessaging

private val TOPIC = "breakfast"
private lateinit var binding: FragmentMainBinding
private lateinit var viewModel: MainViewModel

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        //TODO: Step 2.6
        createChannel(getString(R.string.egg_notification_channel_id), getString(R.string.egg_notification_channel_name))

        //TODO: Step 13.3
        createChannel(getString(R.string.breakfast_notification_channel_id), getString(R.string.breakfast_notification_channel_name))

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
            }else{
                //TODO: Step 7.2
               notificationManager.cancelNotifications()
            }
        }

        //TODO: Step 14.2
        subscribeTopic()

        return binding.root
    }

    //TODO: Step 2.1
    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 2.2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
            )
//TODO: Step 11.1
                    .apply{
                        setShowBadge(false)
                    }

//TODO: Step 2.3
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time for breakfast"

//TODO: Step 2.4
            val notificationManager = requireActivity().getSystemService(
                    NotificationManager::class.java
            )

//TODO: Step 2.5
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

    //TODO: Step 14.1
    private fun subscribeTopic(){
    // [START subscribe_topics]
    FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        /** addOnCompleteListener is to get notified back from FCM on if your suscription is succeeded
            or failed */
        .addOnCompleteListener { task ->
            var msg = getString(R.string.message_subscribed)
            if (!task.isSuccessful) {
                msg = getString(R.string.message_subscribe_failed)
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    // [END subscribe_topics]
    }
}