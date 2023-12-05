package com.hungrydevops.app.activity

import android.os.Bundle
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
    private val quizQuestions= mutableListOf<Quiz>()
    private var currentQuestionIndex = 0
    private var selectedOptionIndex = -1
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

            db.collection("quiz").whereEqualTo("topic", intent.getStringExtra("topic")).get()
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
        binding.option1.setText(currentQuestion.options[0])
        binding.option2.setText(currentQuestion.options[1])
        binding.option3.setText(currentQuestion.options[2])
        binding.option4.setText(currentQuestion.options[3])
        binding.btnRadioGroup.setOnCheckedChangeListener(){_, checkedId ->
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
        binding.btnSubmit.setOnClickListener{
            //dialog open
            QuizManager(this).clearQuizState()
            showQuizResults()
        }
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

    private fun showQuizResults() {
//        startActivity(Intent(this, ResultsActivity::class.java))
//        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        //dialog to be opened
        QuizManager(this).clearQuizState()
    }
}