package com.hungrydevops.app.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hungrydevops.app.Adapter.HandsonReceipeAdapter
import com.hungrydevops.app.Adapter.QuizAdapter
import com.hungrydevops.app.activity.HandsonActivity
import com.hungrydevops.app.activity.MainActivity
import com.hungrydevops.app.base.BaseFragment
import com.hungrydevops.app.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var quizAdapter: QuizAdapter
    private lateinit var handsonAdapter: HandsonReceipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        binding.tvWelcome.setText("Welcome "+"${getSharedPreferences("full_name")}"+" 👋")

        getBannerImageUrls { imageUrls ->
            val imageList = imageUrls.map { SlideModel(it, ScaleTypes.FIT) }
            binding.imgSlider.setImageList(imageList)
        }

//        home fragment
        binding.rvHandsonReceipe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        handsonAdapter = HandsonReceipeAdapter(requireContext())
        binding.rvHandsonReceipe.adapter=handsonAdapter
        binding.tvShowAll1.setOnClickListener {
            startActivity(Intent(context,HandsonActivity::class.java))
        }

        getHandson()

//        quiz fragment
        binding.rvQuiz.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        quizAdapter = QuizAdapter(requireContext())
        binding.rvQuiz.adapter=quizAdapter
        binding.tvShowAll3.setOnClickListener {
            (activity as MainActivity).openQuiz()
        }

        getSubjects()


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.tvWelcome.setText("Welcome "+"${getSharedPreferences("full_name")}"+" 👋")
    }

    private fun getSubjects(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        binding.shimmerLayout1.startShimmer()
        binding.shimmerLayout2.startShimmer()

        showLoading()
        db.collection("quiz").document("topquiz").get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.exists()) {
                    val subjectList= mutableListOf<Pair<String,String>>()
                    val data = querySnapshot.data?.get("topquiz") as Map<String,String>
                    data.forEach { s, s2 ->
                        subjectList.add(Pair(s,s2))
                    }
                        quizAdapter.addItems(subjectList)
                    binding.shimmerLayout1.stopShimmer()
                    binding.shimmerLayout1.visibility=View.INVISIBLE
                    binding.shimmerLayout2.stopShimmer()
                    binding.shimmerLayout2.visibility=View.INVISIBLE
                    hideLoading()
                }
            }
            .addOnFailureListener { exception ->
                hideLoading()
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
            }
    }
    private fun getHandson(){

        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        db.collection("quiz").document("tophandson").get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.exists()) {
                    val subjectList= mutableListOf<String>()
                    val data = querySnapshot.data?.get("tophandson")

                    if (data is Collection<*>) {
                        val limitedList = data.filterIsInstance<String>().take(5)
                        subjectList.addAll(limitedList)
                        handsonAdapter.addItems(subjectList)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getBannerImageUrls(onComplete: (List<String>) -> Unit) {
        val storage = Firebase.storage
        val storageRef = storage.reference.child("banner")

        storageRef.listAll()
            .addOnSuccessListener { listResult ->
                val tasks = listResult.items.map { it.downloadUrl }
                Tasks.whenAllComplete(tasks)
                    .addOnSuccessListener { result ->
                        val imageUrls = result.map { (it as Task<Uri>).result.toString() }
                        onComplete(imageUrls)
                    }
            }
            .addOnFailureListener { exception ->
                onComplete(emptyList())
            }
    }
}