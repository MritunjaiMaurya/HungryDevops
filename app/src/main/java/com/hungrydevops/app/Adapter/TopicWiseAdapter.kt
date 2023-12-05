package com.hungrydevops.app.Adapter

import android.adservices.topics.Topic
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopicWiseQuizRowBinding

class TopicWiseAdapter(val context:Context) : RecyclerView.Adapter<TopicWiseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicWiseAdapter.ViewHolder {
        return ViewHolder(TopicWiseQuizRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: TopicWiseAdapter.ViewHolder, position: Int) {
        holder.binding.constraint.setOnClickListener{
            context.startActivity(Intent(context,QuizActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHolder(val binding : TopicWiseQuizRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}