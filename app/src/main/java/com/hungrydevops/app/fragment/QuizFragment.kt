package com.hungrydevops.app.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hungrydevops.app.Adapter.QuizerCategoryAdapter
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding : FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

//        binding.tv.setOnClickListener {
//            startActivity(Intent(context,QuizActivity::class.java))
//        }

        binding.rvQuizer.layoutManager = LinearLayoutManager(context)
        val adapter= QuizerCategoryAdapter(requireContext())
        binding.rvQuizer.adapter=adapter

        return binding.root
    }
}