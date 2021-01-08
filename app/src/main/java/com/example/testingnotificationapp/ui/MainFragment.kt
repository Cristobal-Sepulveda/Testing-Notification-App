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
import androidx.databinding.DataBindingUtil
import com.example.testingnotificationapp.R
import com.example.testingnotificationapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        //TODO: Step 2.6
        createChannel(
                getString(R.string.egg_notification_channel_id),
                getString(R.string.egg_notification_channel_name)
        )

        return binding.root
    }

    //TODO: Step 2.1
    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 2.2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_LOW
            )
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
}