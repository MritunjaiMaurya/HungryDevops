package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import com.hungrydevops.app.Adapter.ImageSlideAdapter
import com.hungrydevops.app.ItemImageSlider
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityOnboardingBinding


class OnboardingActivity : BaseActivity() {

    val binding by lazy{
        ActivityOnboardingBinding.inflate(layoutInflater)
    }
    private var currentPage = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val item= mutableListOf<ItemImageSlider>()
        item.add(0, ItemImageSlider("Everything is here to enjoy quiz!",
            "Quiz as a group or individually! Expand your circle!", R.drawable.onboard_2)
        )
        item.add(1, ItemImageSlider("Test your knowledge, Quiz Master!",
            "Challenge yourself with a variety of quizzes!", R.drawable.onboard_z))
        item.add(2, ItemImageSlider("Elevate your quiz experience!",
            "Unleash your trivia prowess with Hungry Devops!", R.drawable.onboard_c))

        val viewPagerAdapter = ImageSlideAdapter(this, item)
        binding.viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.autoslide(binding.viewPager)

        binding.btnSignIn.setSingleClickListener {
            startActivity(Intent(this@OnboardingActivity, LoginActivity::class.java))
            finish()
        }
        binding.btnSignup.setSingleClickListener {
            startActivity(Intent(this@OnboardingActivity, CreateAccountActivity::class.java))
            finish()
        }

        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        handler.removeCallbacks(runnable)
//    }
}