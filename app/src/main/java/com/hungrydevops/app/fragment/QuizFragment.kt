package com.hungrydevops.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.Adapter.QuizerCategoryAdapter
import com.hungrydevops.app.base.BaseFragment
import com.hungrydevops.app.databinding.FragmentQuizBinding

class QuizFragment : BaseFragment() {

    private lateinit var binding : FragmentQuizBinding
    val subjectList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        getSubjects()

        return binding.root
    }

    private fun getSubjects(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        showLoading()
        db.collection("quiz").document("Subject").get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.exists()) {
                        val data = querySnapshot.data?.get("subject")
                        subjectList.addAll(data as Collection<String>)
                        binding.rvQuizer.layoutManager = LinearLayoutManager(context)
                        val adapter= QuizerCategoryAdapter(requireContext(), subjectList)
                        binding.rvQuizer.adapter=adapter
                    }
                    hideLoading()
                }
                .addOnFailureListener { exception ->
                    hideLoading()
                    Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
                }
        }
    }