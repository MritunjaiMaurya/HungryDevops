package com.hungrydevops.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.databinding.TopQuizRowBinding

class QuizAdapter(val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizAdapter.ViewHolder {
        return ViewHolder(TopQuizRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(val binding : TopQuizRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}