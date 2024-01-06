package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.activity.ContentActivity
import com.hungrydevops.app.databinding.ProtipsRowBinding
import com.hungrydevops.app.databinding.TopProtipsRowBinding

class ProTipsAdapter(val context: Context, val listTips: List<Pair<String, String>>) : RecyclerView.Adapter<ProTipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProTipsAdapter.ViewHolder {
        return ViewHolder(ProtipsRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ProTipsAdapter.ViewHolder, position: Int) {
        holder.binding.title.text= listTips[position].first
        holder.binding.desc.text= Html.fromHtml(listTips[position].second)
        holder.binding.constraint.setOnClickListener {
            val intent= Intent(context, ContentActivity::class.java)
            intent.putExtra("value", listTips[position].first)
            intent.putExtra("collection", "protips")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTips.size
    }

    inner class ViewHolder(val binding : ProtipsRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}