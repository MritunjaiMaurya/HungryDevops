package com.hungrydevops.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hungrydevops.app.databinding.ActivityResultsBinding

class ResultsActivity:AppCompatActivity() {
    val binding by lazy {
        ActivityResultsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvQuestion.text="Total Questions: ${intent.getStringExtra("totalQuestions")}"
        binding.tvScore.text="Total Score: ${intent.getStringExtra("score")}"


    }

}
