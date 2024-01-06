package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.ContentActivity
import com.hungrydevops.app.databinding.InterviewQueRowBinding
import com.hungrydevops.app.databinding.TopInterviewQueRowBinding

class InterviewQueAdapter(val context: Context,val listInterview: List<Pair<String, String>>) : RecyclerView.Adapter<InterviewQueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterviewQueAdapter.ViewHolder {
        return ViewHolder(InterviewQueRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: InterviewQueAdapter.ViewHolder, position: Int) {
        holder.binding.title.text= listInterview[position].first
        holder.binding.desc.text= Html.fromHtml(listInterview[position].second)
        holder.binding.constraint.setOnClickListener {
            val intent=Intent(context,ContentActivity::class.java)
            intent.putExtra("value",listInterview[position].first)
            intent.putExtra("collection", "interview")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listInterview.size
    }

    class ViewHolder(val binding : InterviewQueRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}