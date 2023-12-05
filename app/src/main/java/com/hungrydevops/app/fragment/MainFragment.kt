package com.hungrydevops.app.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hungrydevops.app.Adapter.HandsonReceipeAdapter
import com.hungrydevops.app.Adapter.InterviewQueAdapter
import com.hungrydevops.app.Adapter.ProTipsAdapter
import com.hungrydevops.app.Adapter.QuizAdapter
import com.hungrydevops.app.activity.MainActivity
import com.hungrydevops.app.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        binding.tvWelcome.setText("Welcome "+"Prateek"+" 👋")

//        home fragment
        binding.rvHandsonReceipe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HandsonReceipeAdapter(requireContext())
        binding.rvHandsonReceipe.adapter=adapter

//        interview que fragment
        binding.rvInterviewQuestion.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter2 = InterviewQueAdapter(requireContext())
        binding.rvInterviewQuestion.adapter=adapter2
        binding.tvShowAll2.setOnClickListener {
            (activity as MainActivity).openInterviewQuestion()
        }

//        quiz fragment
        binding.rvQuiz.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter3 = QuizAdapter(requireContext())
        binding.rvQuiz.adapter=adapter3
        binding.tvShowAll3.setOnClickListener {
            (activity as MainActivity).openQuiz()
        }

//        pro tips fragment
        binding.rvProTips.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter4 = ProTipsAdapter(requireContext())
        binding.rvProTips.adapter=adapter4
        binding.tvShowAll4.setOnClickListener {
            (activity as MainActivity).openProtips()
        }


        return binding.root
    }
}