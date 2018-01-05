package com.shreya.batterystatusindicator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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


    }

    fun updateBatteryLevel(){
        var batteryBroadcastReceiver : BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                context?.unregisterReceiver(this)

                val rawlevel = intent?.getIntExtra("level", -1)
                val scale = intent?.getIntExtra("scale", -1)

                val level = -1

                /*if(rawlevel?. >= 0 && scale > 0)

                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;*/

            }

        }


    }
}
