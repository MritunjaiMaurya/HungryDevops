package com.hungrydevops.app.common

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.hungrydevops.app.data.Quiz

class FirestoreHelper {
        private val db = FirebaseFirestore.getInstance()
        private val quizCollection: CollectionReference = db.collection("quiz")

        fun getQuizzes(listener: OnQuizzesLoadedListener) {
            quizCollection.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.toObjects(Quiz::class.java)?.let { listener.onQuizzesLoaded(it) }
                } else {
                    listener.onQuizzesError(task.exception!!)
                }
            }
        }

        interface OnQuizzesLoadedListener {
            fun onQuizzesLoaded(quizzes: List<Quiz>)
            fun onQuizzesError(e: Exception)
        }
    }