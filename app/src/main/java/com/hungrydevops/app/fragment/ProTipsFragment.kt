package com.hungrydevops.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.Adapter.ProTipsAdapter
import com.hungrydevops.app.base.BaseFragment
import com.hungrydevops.app.databinding.FragmentProTipsBinding

class ProTipsFragment : BaseFragment() {
    private lateinit var binding : FragmentProTipsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProTipsBinding.inflate(inflater, container, false)

        getTips()

        return binding.root
    }

    private fun getTips(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        showLoading()
        db.collection("protips").get()
            .addOnSuccessListener {
                val proTipsList= mutableListOf<Pair<String,String>>()
                it.forEach {
                    val title= it.id
                    val description = it.data.get("description").toString()
                    proTipsList.add(Pair(title,description))
                }
                    binding.rvProTips.layoutManager = LinearLayoutManager(context)
                    val adapter= ProTipsAdapter(requireContext(), proTipsList)
                    binding.rvProTips.adapter=adapter
                hideLoading()
            }
            .addOnFailureListener { exception ->
                hideLoading()
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
            }
    }
}