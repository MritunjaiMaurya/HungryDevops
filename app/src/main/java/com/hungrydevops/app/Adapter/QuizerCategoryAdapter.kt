package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.databinding.QuizerCategoryRowBinding

class QuizerCategoryAdapter(val context: Context, val listSub: List<String>) : RecyclerView.Adapter<QuizerCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): QuizerCategoryAdapter.ViewHolder {
        return ViewHolder(QuizerCategoryRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: QuizerCategoryAdapter.ViewHolder, position: Int) {
        holder.binding.tvName.text=listSub[position]
        holder.binding.constraint.setOnClickListener {
            val intent=Intent(context,ChooseTopicActivity::class.java)
            intent.putExtra("topic", holder.binding.tvName.text)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listSub.size
    }

    class ViewHolder(val binding :QuizerCategoryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}