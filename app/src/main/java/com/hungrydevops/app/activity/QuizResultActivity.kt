package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityQuizResultBinding

class QuizResultActivity : BaseActivity() {
    val binding by lazy {
        ActivityQuizResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val topic=intent.getStringExtra("topic")
        val total= intent.getStringExtra("totalQuestions")?.toInt()?:0
        val correct= intent.getStringExtra("score")?.toInt()?:0
        val wrong= total - correct
        binding.tvTotalQueValue.text=total.toString()
        binding.tvCorrectValue.text=correct.toString()
        binding.tvWrongValue.text=wrong.toString()
        binding.tvPoints.text="${(correct*100)/total}%"

        binding.imgBack.setOnClickListener { finish() }

        binding.imgHome.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.imgPlayAgain.setOnClickListener { val intent=Intent(this, QuizActivity::class.java)
        intent.putExtra("topic",topic)
        startActivity(intent)
        finish()}

        binding.imgShareScore.setOnClickListener {  val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this great app!")
            val appLink = "https://play.google.com/store/apps/details?id=com.hungrydevops.app"
            putExtra(
                Intent.EXTRA_TEXT,
                "Hey!! My score is ${(correct*100)/total}% in $topic . What's your?. Check it out here: $appLink"
            )
        }
            val chooser = Intent.createChooser(shareIntent, "Share App via....")
            startActivity(chooser) }

        binding.imgSeeAnswers.setOnClickListener {  }
    }
}