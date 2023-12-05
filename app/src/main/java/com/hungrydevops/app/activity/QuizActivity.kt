package com.hungrydevops.app.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.R
import com.hungrydevops.app.databinding.ActivityQuizBinding


class QuizActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityQuizBinding.inflate(layoutInflater)
    }
    private val quizQuestions= mutableListOf<Quiz>()
    private var currentQuestionIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        getQuestions()

    }

    private fun getQuestions():List<Quiz>{

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        val questionsRef = db.collection("quiz")


            questionsRef.whereEqualTo("topic", intent.getStringExtra("topic")).get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val question = document.toObject(Quiz::class.java)
                        quizQuestions.add(question)
                    }
                    displayCurrentQuestion();
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
                }
        return quizQuestions;
        }

    private fun displayCurrentQuestion() {
        val currentQuestion = quizQuestions.get(currentQuestionIndex)
        binding.tvQueNo.text="Question ${currentQuestionIndex+1}/${quizQuestions.size}"
        binding.tvQuestion.text=currentQuestion.question
        binding.option1.setText(currentQuestion.options[0])
        binding.option2.setText(currentQuestion.options[1])
        binding.option3.setText(currentQuestion.options[2])
        binding.option4.setText(currentQuestion.options[3])
        binding.btnRadioGroup.setOnCheckedChangeListener(){_, checkedId ->
            val selectedOption= findViewById<RadioButton>(checkedId)
        }
        binding.btnNext.setOnClickListener{
            showNextQuestion()
        }
        binding.btnPrevious.setOnClickListener{
            showPreviousQuestion()
        }
    }

    private fun showNextQuestion() {
        // Check if there are more questions
        if (currentQuestionIndex < quizQuestions.size - 1) {
            // Move to the next question
            currentQuestionIndex++
            // Display the new question
            displayCurrentQuestion()
        } else {
            // This was the last question, handle accordingly
            showQuizResults()
        }
    }

    private fun showPreviousQuestion() {
        // Check if there are previous questions
        if (currentQuestionIndex > 0) {
            // Move to the previous question
            currentQuestionIndex--
            // Display the previous question
            displayCurrentQuestion()
        } else {
            // This is the first question, handle accordingly (optional)
            // You might want to disable or hide the "Previous Question" button
        }
    }

    private fun showQuizResults() {
//        startActivity(Intent(this, ResultsActivity::class.java))
//        finish() // Close this activity
    }
    }