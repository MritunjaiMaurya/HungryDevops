package com.hungrydevops.app.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class QuizManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("QuizPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveSelectedOption(questionId: Int, selectedOptionIndex: Int) {
        val questionKey = "question_$questionId"
        sharedPreferences.edit().putInt(questionKey, selectedOptionIndex).apply()
    }

    fun getSelectedOption(questionId: Int): Int {
        val questionKey = "question_$questionId"
        return sharedPreferences.getInt(questionKey, -1)
    }

    fun clearQuizState() {
        sharedPreferences.edit().clear().apply()
    }
}
