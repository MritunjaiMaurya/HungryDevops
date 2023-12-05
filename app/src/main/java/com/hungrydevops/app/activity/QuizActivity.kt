package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hungrydevops.app.R
import com.hungrydevops.app.databinding.ActivityQuizBinding
import com.hungrydevops.app.fragment.QuizFragment

class QuizActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityQuizBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}