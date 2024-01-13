package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ContentActivity
import com.hungrydevops.app.databinding.ProtipsRowBinding

class HandsonAdapter(val context: Context, val list: List<Pair<String, String>>) : RecyclerView.Adapter<HandsonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandsonAdapter.ViewHolder {
        return ViewHolder(ProtipsRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: HandsonAdapter.ViewHolder, position: Int) {
        holder.binding.image.setImageResource(R.drawable.ic_handson)
        holder.binding.title.text= list[position].first
        holder.binding.desc.text= Html.fromHtml(list[position].second)
        holder.binding.constraint.setOnClickListener {
            val intent= Intent(context, ContentActivity::class.java)
            intent.putExtra("value", list[position].first)
            intent.putExtra("collection", "handson")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding : ProtipsRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
