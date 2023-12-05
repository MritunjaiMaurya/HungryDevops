package com.hungrydevops.app.Adapter

import android.adservices.topics.Topic
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopicWiseQuizRowBinding

class TopicWiseAdapter(val context:Context, val listTopic: List<String>) : RecyclerView.Adapter<TopicWiseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicWiseAdapter.ViewHolder {
        return ViewHolder(TopicWiseQuizRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: TopicWiseAdapter.ViewHolder, position: Int) {
        holder.binding.tvName.text=listTopic[position]
        holder.binding.constraint.setOnClickListener {
            val intent=Intent(context, QuizActivity::class.java)
            intent.putExtra("topic", holder.binding.tvName.text)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTopic.size
    }

    class ViewHolder(val binding : TopicWiseQuizRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}