package com.hungrydevops.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hungrydevops.app.databinding.FragmentInterviewQuestionBinding

class InterviewQuestionFragment : Fragment() {

    private lateinit var binding : FragmentInterviewQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInterviewQuestionBinding.inflate(inflater, container, false)





        return binding.root
    }
}