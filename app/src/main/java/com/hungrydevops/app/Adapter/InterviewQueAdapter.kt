package com.hungrydevops.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.databinding.TopInterviewQueRowBinding

class InterviewQueAdapter(val context: Context) : RecyclerView.Adapter<InterviewQueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterviewQueAdapter.ViewHolder {
        return ViewHolder(TopInterviewQueRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: InterviewQueAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder(val binding : TopInterviewQueRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}