package com.hungrydevops.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.Adapter.InterviewQueAdapter
import com.hungrydevops.app.Adapter.ProTipsAdapter
import com.hungrydevops.app.base.BaseFragment
import com.hungrydevops.app.databinding.FragmentInterviewQuestionBinding

class InterviewQuestionFragment : BaseFragment() {

    private lateinit var binding : FragmentInterviewQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInterviewQuestionBinding.inflate(inflater, container, false)

        getInterview()

        return binding.root
    }
    private fun getInterview(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        showLoading()
        db.collection("interview").get()
            .addOnSuccessListener {
                val interviewList= mutableListOf<Pair<String,String>>()
                it.forEach {
                    val title= it.id
                    val description = it.data.get("description").toString()
                    interviewList.add(Pair(title,description))
                }
                binding.rvInterview.layoutManager = LinearLayoutManager(context)
                val adapter= InterviewQueAdapter(requireContext(), interviewList)
                binding.rvInterview.adapter=adapter
                hideLoading()
            }
            .addOnFailureListener { exception ->
                hideLoading()
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
            }
    }
}