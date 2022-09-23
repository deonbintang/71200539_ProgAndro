package com.pemrogandroid.clickrush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tv_time: TextView
    lateinit var tv_clicks: TextView
    lateinit var b_click: Button

    var currentTime = 60
    var currentClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_time = findViewById(R.id.tv_time)
        tv_clicks = findViewById(R.id.tv_clicks)
        b_click = findViewById(R.id.b_click)

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_time.setText("Time Left: " + millisUntilFinished / 1000)
            }
            override fun onFinish() {
                tv_time.setText("Time's up! Your score was: $currentClicks")
            }
        }.start()

        b_click.setOnClickListener {
            currentClicks++
            tv_clicks.text = "Your Score: $currentClicks"

        }
        }
    }