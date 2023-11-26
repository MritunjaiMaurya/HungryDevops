package com.hungrydevops.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hungrydevops.app.R
import com.hungrydevops.app.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding : FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        return binding.root
    }
}