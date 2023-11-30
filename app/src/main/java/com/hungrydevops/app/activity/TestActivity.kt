package com.hungrydevops.app.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.common.FirestoreHelper
import com.hungrydevops.app.data.Quiz
import com.hungrydevops.app.databinding.ActivityTestBinding

class TestActivity:BaseActivity(), FirestoreHelper.OnQuizzesLoadedListener{
    val binding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }
    private val firestoreHelper = FirestoreHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firestoreHelper.getQuizzes(this)
    }

    override fun onQuizzesLoaded(quizzes: List<Quiz>) {
        quizzes?.forEach { quiz ->
            val question = quiz.question
            val options = quiz.option
            val correctAnswer = quiz.answer

            binding.tv1.setText(question)
            binding.tv2.setText(options[0])
            binding.tv3.setText(options[1])
            binding.tv4.setText(options[2])
            binding.tv5.setText(options[3])
            binding.btn2.setOnClickListener{
                if(correctAnswer.equals("Mritunjai"))
                    binding.tv6.visibility= View.VISIBLE
            }
        }
    }

    override fun onQuizzesError(e: Exception) {

    }
}