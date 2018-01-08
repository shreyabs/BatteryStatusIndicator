package com.shreya.batterystatusindicator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar

class BatteryIndicatorActivity : AppCompatActivity() {

    var mBatteryStatusBar : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery_indicator)

        mBatteryStatusBar = findViewById<ProgressBar>(R.id.batterylevel_progbar) as ProgressBar

    }

    override fun onResume() {
        super.onResume()

        //Update the progress bar
        updateBatteryLevel()
    }


    private fun updateBatteryLevel(){
        val batteryBroadcastReceiver : BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                context?.unregisterReceiver(this) //Continuing to monitor the status
                                                     // can lead to battery drain

                val batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) as Int
                val scale = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

                var level = -1

                if(batteryLevel >= 0 && scale > 0){
                    level = (batteryLevel * 100) / scale

                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mBatteryStatusBar?.setProgress(level, false)
                }else{
                    mBatteryStatusBar?.progress = level
                }
            }
        }

        val batteryIntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver, batteryIntentFilter)
    }
}
