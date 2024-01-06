package com.hungrydevops.app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.R
import com.hungrydevops.app.common.QuizManager
import com.hungrydevops.app.databinding.ActivityQuizBinding


class QuizActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityQuizBinding.inflate(layoutInflater)
    }
    private lateinit var countDownTimer: CountDownTimer
    private val quizQuestions= mutableListOf<Quiz>()
    private var currentQuestionIndex = 0
    private var selectedOptionIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSubmit.setOnClickListener {
            showQuizResults()
        }

        getQuestions()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun getQuestions():List<Quiz>{

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        intent.getStringExtra("topic")?.let {topic->
            db.collection("quiz").document(topic).get()
                .addOnSuccessListener {
                    val questionsList = it[topic] as? List<Map<String, Any>>
                    questionsList?.forEach { questionData ->
                        val question = questionData["question"] as? String ?: ""
                        val answer = questionData["answer"] as? String ?: ""
                        val options = (questionData["option"] as? List<*>)?.map { it.toString() } ?: emptyList()

                        val quizQuestion = Quiz(question, options , answer)
                        quizQuestions.add(quizQuestion)
                    }
                    quizTimer(quizQuestions.size)
                    displayCurrentQuestion();
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
                }
        }
        return quizQuestions;
        }

    private fun quizTimer(size: Int) {
        val sizeInSeconds = size * 60

        countDownTimer = object : CountDownTimer((sizeInSeconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                binding.tvTimer.text = String.format("%02d\n%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.tvTimer.text = "00\n00"
                showQuizResults()
            }
        }
        countDownTimer.start()

    }

    private fun displayCurrentQuestion() {
        val savedOption=QuizManager(this).getSelectedOption(currentQuestionIndex+1 )
        binding.btnRadioGroup.check(
            when(savedOption){
                1-> R.id.option1
                2-> R.id.option2
                3-> R.id.option3
                4-> R.id.option4
                else -> -1
            }
        )
        val currentQuestion = quizQuestions.get(currentQuestionIndex)
        binding.tvQueNo.text="Question ${currentQuestionIndex+1}/${quizQuestions.size}"
        binding.tvQuestion.text=currentQuestion.question
        binding.option1.setText(currentQuestion.option[0])
        binding.option2.setText(currentQuestion.option[1])
        binding.option3.setText(currentQuestion.option[2])
        binding.option4.setText(currentQuestion.option[3])
        binding.btnRadioGroup.setOnCheckedChangeListener{_, checkedId ->
            when (checkedId) {
                R.id.option1 -> {
                    selectedOptionIndex=1
                }
                R.id.option2 -> {
                    selectedOptionIndex=2
                }
                R.id.option3 -> {
                    selectedOptionIndex=3
                }
                R.id.option4 -> {
                    selectedOptionIndex=4
                }
            }
            QuizManager(this).saveSelectedOption(currentQuestionIndex+1, selectedOptionIndex)
        }
        if(currentQuestionIndex==0){
        binding.btnPrevious.visibility= View.INVISIBLE
        binding.tvPrevious.visibility=View.INVISIBLE
        }
        else{
            binding.btnPrevious.visibility= View.VISIBLE
            binding.tvPrevious.visibility=View.VISIBLE
        }
        if(currentQuestionIndex==(quizQuestions.size-1)){
            binding.btnNext.visibility= View.INVISIBLE
            binding.tvNext.visibility=View.INVISIBLE
        }
        else{
            binding.btnNext.visibility= View.VISIBLE
            binding.tvNext.visibility=View.VISIBLE
        }
        binding.btnNext.setOnClickListener{ showNextQuestion() }
        binding.btnPrevious.setOnClickListener{ showPreviousQuestion() }
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < quizQuestions.size - 1) {
            currentQuestionIndex++
            displayCurrentQuestion()
        }
    }

    private fun showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            displayCurrentQuestion()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showQuizResults() {

        countDownTimer.cancel()

    val score = calculateScore()
    val totalQuestions = quizQuestions.size

    val intent = Intent(this, ResultsActivity::class.java)
    intent.putExtra("score", score.toString())
    intent.putExtra("totalQuestions", totalQuestions.toString())
    startActivity(intent)
    finish()
        QuizManager(this).clearQuizState()
    }

    private fun calculateScore(): Int {
        var score = 0
        quizQuestions.forEach {quiz->
            val selected=QuizManager(this).getSelectedOption(quizQuestions.indexOf(quiz)+1)
            if (quiz.answer==selected.toString()){
                score++
            }
        }
        return score
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        QuizManager(this).clearQuizState()
    }
}