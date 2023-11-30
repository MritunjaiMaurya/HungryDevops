package com.hungrydevops.app.activity

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityMainBinding
import com.hungrydevops.app.fragment.InterviewQuestionFragment
import com.hungrydevops.app.fragment.MainFragment
import com.hungrydevops.app.fragment.ProTipsFragment
import com.hungrydevops.app.fragment.ProfileFragment
import com.hungrydevops.app.fragment.QuizFragment

class MainActivity : BaseActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var backPressedTime: Long = 0

    private var lastClickTime: Long = 0
    private val throttleInterval: Long = 500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(MainFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val currentClickTime = SystemClock.elapsedRealtime()
            if (currentClickTime - lastClickTime < throttleInterval) {
                return@setOnItemSelectedListener false
            }
            lastClickTime = currentClickTime

            when (item.itemId) {
                R.id.bottom_home -> replaceFragment(MainFragment())
                R.id.bottom_interview_que -> replaceFragment(InterviewQuestionFragment())
                R.id.bottom_quiz -> replaceFragment(QuizFragment())
                R.id.bottom_pro_tips -> replaceFragment(ProTipsFragment())
                R.id.bottom_profile -> replaceFragment(ProfileFragment())
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }
    fun replaceFragment(fragment: androidx.fragment.app.Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}