package com.hungrydevops.app.activity

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.Adapter.HandsonAdapter
import com.hungrydevops.app.Adapter.ProTipsAdapter
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityHandsonBinding

class HandsonActivity : BaseActivity() {
    val binding by lazy {
        ActivityHandsonBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener { finish() }

        getTips()
    }

    private fun getTips(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        showLoading()
        db.collection("handson").get()
            .addOnSuccessListener {
                val proTipsList= mutableListOf<Pair<String,String>>()
                it.forEach {
                    val title= it.id
                    val description = it.data.get("description").toString()
                    proTipsList.add(Pair(title,description))
                }
                binding.rvProTips.layoutManager = LinearLayoutManager(this)
                val adapter= HandsonAdapter(this, proTipsList)
                binding.rvProTips.adapter=adapter
                hideLoading()
            }
            .addOnFailureListener { exception ->
                hideLoading()
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
            }
    }
}