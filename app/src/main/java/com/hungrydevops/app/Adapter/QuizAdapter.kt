package com.hungrydevops.app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopHandsonReceipeRowBinding
import com.hungrydevops.app.databinding.TopQuizRowBinding

class QuizAdapter(val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    val quizList= mutableListOf<String>()
    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list:List<String>){
        quizList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizAdapter.ViewHolder {
        return ViewHolder(TopHandsonReceipeRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {
        holder.binding.title.text=quizList[position]
        holder.binding.image.setOnClickListener {
            val intent= Intent(context, QuizActivity::class.java)
            intent.putExtra("topic", quizList[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    inner class ViewHolder(val binding : TopHandsonReceipeRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}