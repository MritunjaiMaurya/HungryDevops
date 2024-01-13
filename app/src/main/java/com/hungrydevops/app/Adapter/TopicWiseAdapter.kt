package com.hungrydevops.app.Adapter

import android.adservices.topics.Topic
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopicWiseQuizRowBinding

class TopicWiseAdapter(val context:Context, val listTopic: List<String>,val value:String) : RecyclerView.Adapter<TopicWiseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicWiseAdapter.ViewHolder {
        return ViewHolder(TopicWiseQuizRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: TopicWiseAdapter.ViewHolder, position: Int) {
        holder.binding.title.text=listTopic[position]
        holder.binding.constraint.setOnClickListener {
            val intent=Intent(context, QuizActivity::class.java)
            intent.putExtra("topic", listTopic[position])
            context.startActivity(intent)
        }

        holder.binding.image.setImageResource(
            when(value){
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
        holder.binding.cardView1.setCardBackgroundColor(context.resources.getColor(when(value){
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
        return listTopic.size
    }

    class ViewHolder(val binding : TopicWiseQuizRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}