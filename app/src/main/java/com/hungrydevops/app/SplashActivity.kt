package com.hungrydevops.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.hungrydevops.app.activity.OnboardingActivity
import com.hungrydevops.app.databinding.ActivitySplashBinding

class SplashActivity:AppCompatActivity(){

    val binding by lazy{
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration=1000
        animation.startOffset=1000
        animation.fillAfter=true
        binding.imgSplash.startAnimation(animation)


        Handler().postDelayed({startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
        },3000)


    }
}