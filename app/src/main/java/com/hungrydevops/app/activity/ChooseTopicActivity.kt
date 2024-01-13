package com.hungrydevops.app.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.Adapter.QuizerCategoryAdapter
import com.hungrydevops.app.Adapter.TopicWiseAdapter
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityChooseTopicBinding

class ChooseTopicActivity : BaseActivity() {

    val binding by lazy {
        ActivityChooseTopicBinding.inflate(layoutInflater)
    }
    val topicList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setSingleClickListener {
            onBackPressed()
        }

        binding.rvTopicCategory.layoutManager = GridLayoutManager(this, 2)
        getTopics()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun getTopics() {

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        val questionsRef = db.collection("quiz")

        val topic = intent.getStringExtra("topic") ?: ""

        binding.title.text=topic

        try {
            questionsRef.document(topic).get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.exists()) {
                        val data = querySnapshot.data?.get("topic")
                        topicList.addAll(data as Collection<String>)
                        val adapter = TopicWiseAdapter(this, topicList, topic)
                        binding.rvTopicCategory.adapter = adapter
                    } else {
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        } catch (e:Exception){
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
        }
    }
}