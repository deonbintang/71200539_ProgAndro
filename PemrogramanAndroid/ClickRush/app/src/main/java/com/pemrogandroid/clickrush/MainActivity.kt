package com.pemrogandroid.clickrush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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
            val animation = AnimationUtils.loadAnimation(this, R.anim.scaling)
            b_click.startAnimation(animation)
        }

        }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_menu){
            Toast.makeText(applicationContext, "created by 71200539_Deon", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("savedInt", currentClicks)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tv_clicks.text = currentClicks.toString()
    }
}