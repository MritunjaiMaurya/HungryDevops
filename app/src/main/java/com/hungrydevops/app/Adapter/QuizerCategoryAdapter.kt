package com.hungrydevops.app.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.databinding.QuizerCategoryRowBinding

class QuizerCategoryAdapter(val context: Context, val listSub: List<String>) : RecyclerView.Adapter<QuizerCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        return ViewHolder(QuizerCategoryRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text=listSub[position]
        holder.binding.constraint.setOnClickListener {
            val intent=Intent(context,ChooseTopicActivity::class.java)
            intent.putExtra("topic", holder.binding.tvName.text)
            context.startActivity(intent)
        }
        holder.binding.image.setImageResource(
            when(listSub[position]){
                "Jenkins" -> R.drawable.ic_jenkins
                "Kubernetes" -> R.drawable.ic_kubernetes
                "Ansible" -> R.drawable.ic_ansible
                "Docker" -> R.drawable.ic_docker
                "Aws" -> R.drawable.ic_aws
                "Git" -> R.drawable.ic_git
                "Puppet" -> R.drawable.ic_puppet
                "Chef" -> R.drawable.ic_chef
                "Terraform" -> R.drawable.ic_terraform
                "CI CD" -> R.drawable.ic_cicd
                else -> R.drawable.ic_other
            }
        )
        holder.binding.cardView1.setCardBackgroundColor(context.resources.getColor(when(listSub[position]){
            "Jenkins" -> R.color.jenkins
            "Kubernetes" -> R.color.kubernetes
            "Ansible" -> R.color.gray
            "Docker" -> R.color.docker
            "Aws" -> R.color.aws
            "Git" -> R.color.git
            "Puppet" -> R.color.puppet
            "Chef" -> R.color.chef
            "Terraform" -> R.color.gray
            "CI CD" -> R.color.cicd
            else -> R.color.other
        }))
    }

    override fun getItemCount(): Int {
        return listSub.size
    }

    class ViewHolder(val binding :QuizerCategoryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}