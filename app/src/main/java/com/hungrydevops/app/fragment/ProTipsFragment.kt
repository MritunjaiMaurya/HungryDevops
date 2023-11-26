package com.hungrydevops.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hungrydevops.app.R
import com.hungrydevops.app.databinding.FragmentProTipsBinding

class ProTipsFragment : Fragment() {
    private lateinit var binding : FragmentProTipsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProTipsBinding.inflate(inflater, container, false)


        return binding.root
    }
}