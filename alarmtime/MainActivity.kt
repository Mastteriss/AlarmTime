package com.example.alarmtime

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var alarmTimeTV:TextView
    private lateinit var alarmTimeET:EditText
    private lateinit var setAlarmButtonBTN:Button
    private lateinit var exitButtonBTN:Button
    private lateinit var alarmManager: AlarmManager
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmTimeTV = findViewById(R.id.alarmTimeTV)
        alarmTimeET = findViewById(R.id.alarmTimeET)
        setAlarmButtonBTN = findViewById(R.id.stopAlarmButton)
        exitButtonBTN = findViewById(R.id.exitButtonBTN)

        setAlarmButtonBTN.setOnClickListener {
            val time = alarmTimeET.text.toString()
            if (time.isNotEmpty()) {
                alarmTimeTV.text = "Установленное время: $time"
                setAlarm(time)
            } else {
                Toast.makeText(this, "Введите время будильника", Toast.LENGTH_SHORT).show()
            }
        }
        exitButtonBTN.setOnClickListener {
            finish()
        }
    }


    private fun setAlarm(time: String) {
        val parts = time.split(":")
        if (parts.size == 2) {
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (before(Calendar.getInstance())) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }
            val intent = Intent(this, AlarmReceiver::class.java).apply {
                putExtra("alarmTime", time)

            }
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                System.currentTimeMillis().toInt(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            Toast.makeText(this, "Будильник установлен на $time", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Неверный формат времени", Toast.LENGTH_SHORT).show()
        }
    }


    }
