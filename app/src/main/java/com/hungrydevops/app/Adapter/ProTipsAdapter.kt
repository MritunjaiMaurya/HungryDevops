package com.hungrydevops.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.databinding.TopProtipsRowBinding

class ProTipsAdapter(val context: Context) : RecyclerView.Adapter<ProTipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProTipsAdapter.ViewHolder {
        return ViewHolder(TopProtipsRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ProTipsAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(val binding : TopProtipsRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}