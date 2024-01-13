package com.hungrydevops.app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.ContentActivity
import com.hungrydevops.app.activity.HandsonActivity
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopHandsonReceipeRowBinding

class HandsonReceipeAdapter(val context: Context) : RecyclerView.Adapter<HandsonReceipeAdapter.ViewHolder>() {

    val handsonList= mutableListOf<String>()
    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list:List<String>){
        handsonList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): HandsonReceipeAdapter.ViewHolder {
      return ViewHolder(TopHandsonReceipeRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: HandsonReceipeAdapter.ViewHolder, position: Int) {
        holder.binding.title.text=handsonList[position]
        holder.binding.image.setOnClickListener {
            val intent= Intent(context, ContentActivity::class.java)
            intent.putExtra("value", holder.binding.title.text)
            intent.putExtra("collection", "handson")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return handsonList.size

    }

    inner class ViewHolder(val binding : TopHandsonReceipeRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

}