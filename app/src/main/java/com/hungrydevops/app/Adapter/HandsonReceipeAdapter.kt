package com.hungrydevops.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.databinding.TopHandsonReceipeRowBinding

class HandsonReceipeAdapter(val context: Context) : RecyclerView.Adapter<HandsonReceipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): HandsonReceipeAdapter.ViewHolder {
      return ViewHolder(TopHandsonReceipeRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: HandsonReceipeAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

        return 10

    }

    inner class ViewHolder(val binding : TopHandsonReceipeRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

}