package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.databinding.QuizerCategoryRowBinding

class QuizerCategoryAdapter(val context: Context) : RecyclerView.Adapter<QuizerCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): QuizerCategoryAdapter.ViewHolder {
        return ViewHolder(QuizerCategoryRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: QuizerCategoryAdapter.ViewHolder, position: Int) {
        holder.binding.constraint.setOnClickListener {
            context.startActivity(Intent(context,ChooseTopicActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    class ViewHolder(val binding :QuizerCategoryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}