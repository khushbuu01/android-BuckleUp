package com.example.android_buzz_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_startup.*

@Suppress("DEPRECATION")
class StartupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val applicationName: TextView = findViewById(R.id.buckle_up)
        val tagline: TextView = findViewById(R.id.tag)
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        applicationName.startAnimation(topAnim)
        tagline.startAnimation(topAnim)

        val appIcon: ImageView = findViewById(R.id.icon)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        appIcon.startAnimation(bottomAnim)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}