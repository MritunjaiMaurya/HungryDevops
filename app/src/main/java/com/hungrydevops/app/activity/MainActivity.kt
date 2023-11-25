package com.hungrydevops.app.activity

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0

    private var lastClickTime: Long = 0
    private val throttleInterval: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.frameLayout))

//        replaceFragment(MainFragment())
//
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            val currentClickTime = SystemClock.elapsedRealtime()
//            if (currentClickTime - lastClickTime < throttleInterval) {
//                return@setOnItemSelectedListener false
//            }
//            lastClickTime = currentClickTime
//
//            when (item.itemId) {
//                R.id.bottom_home -> replaceFragment(MainFragment())
//                R.id.bottom_handson_experience -> replaceFragment(HandsonReceipeFragment())
//                R.id.bottom_quiz -> replaceFragment(QuizFragment())
//                R.id.bottom_interview_que -> replaceFragment(InterviewQuestionFragment())
//                R.id.bottom_pro_tips -> replaceFragment(ProTipsFragment())
//                R.id.bottom_profile -> replaceFragment(ProfileFragment())
//                else -> return@setOnItemSelectedListener false
//            }
//            true // return true to display the item as the selected item
//        }
    }
//    fun replaceFragment(fragment: androidx.fragment.app.Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout,fragment)
//        fragmentTransaction.commit()
//    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}