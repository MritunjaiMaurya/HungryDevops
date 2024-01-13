package com.hungrydevops.app.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(MainFragment())

        binding.bottomNavigation.setOnSingleNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> replaceFragment(MainFragment())
                R.id.bottom_interview_que -> replaceFragment(InterviewQuestionFragment())
                R.id.bottom_quiz -> replaceFragment(QuizFragment())
                R.id.bottom_pro_tips -> replaceFragment(ProTipsFragment())
                R.id.bottom_profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }
        val a = AnimationUtils.loadAnimation(this, R.anim.blink)
        a.reset()
        binding.tvVisitWebsite.clearAnimation()
        binding.tvVisitWebsite.startAnimation(a)

        binding.tvVisitWebsite.setSingleClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://hungrydevops.com/")
            try{
                startActivity(intent)
            } catch(e:Exception){
                Toast.makeText(this,"Download any browser to open website",Toast.LENGTH_SHORT).show()
            } }

    }
    fun replaceFragment(fragment: androidx.fragment.app.Fragment){
        try{
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout,fragment)
            fragmentTransaction.commit()
        }catch (e : Exception){}

    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
    fun openQuiz(){
        binding.bottomNavigation.selectedItemId=R.id.bottom_quiz
    }

}