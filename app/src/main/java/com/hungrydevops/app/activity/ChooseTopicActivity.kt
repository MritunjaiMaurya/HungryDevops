package com.hungrydevops.app.activity

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

    val binding by lazy{
        ActivityChooseTopicBinding.inflate(layoutInflater)
    }
    val topicList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setSingleClickListener {
            onBackPressed()
        }

        binding.rvTopicCategory.layoutManager=GridLayoutManager(this,2)
        getTopics()

    }
    private fun getTopics(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        val questionsRef = db.collection("quiz")

        intent.getStringExtra("topic")?.let {
            questionsRef.document(it).get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.exists()) {
                    val data = querySnapshot.data?.get("topic")
                    topicList.addAll(data as Collection<String>)
                    val adapter= TopicWiseAdapter(this, topicList)
                    binding.rvTopicCategory.adapter=adapter
                } else {
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
}