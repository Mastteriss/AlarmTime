package com.example.alarmtime

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AlarmActivity : AppCompatActivity() {
    private lateinit var stopAlarmButton: Button
    private lateinit var exitButtonBTN: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alarm_ringing)
        stopAlarmButton = findViewById(R.id.stopAlarmButton)
        exitButtonBTN = findViewById(R.id.exitButtonBTN)

        Toast.makeText(this,"Будильник выключен", Toast.LENGTH_SHORT).show()
        finish()

        exitButtonBTN.setOnClickListener {
            finish()
        }
    }
}