package com.hungrydevops.app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.ChooseTopicActivity
import com.hungrydevops.app.activity.QuizActivity
import com.hungrydevops.app.databinding.TopHandsonReceipeRowBinding
import com.hungrydevops.app.databinding.TopQuizRowBinding

class QuizAdapter(val context: Context) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    val quizList= mutableListOf<Pair<String,String>>()
    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list:List<Pair<String,String>>){
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
        holder.binding.title.text=quizList[position].first
        holder.binding.image.setOnClickListener {
            val intent= Intent(context, QuizActivity::class.java)
            intent.putExtra("topic", quizList[position])
            context.startActivity(intent)
        }
        holder.binding.image.setImageResource(
            when(quizList[position].second){
                "Jenkins" -> R.drawable.ic_jenkins
                "Kubernetes" -> R.drawable.ic_kubernetes
                "Ansible" -> R.drawable.ic_ansible
                "Docker" -> R.drawable.ic_docker
                "Aws" -> R.drawable.ic_aws
                "Git" -> R.drawable.ic_git
                "Puppet" -> R.drawable.ic_puppet
                "Chef" -> R.drawable.ic_chef
                "Terraform" -> R.drawable.ic_terraform
                "CI/CD" -> R.drawable.ic_cicd
                else -> R.drawable.ic_other
            }
        )
        holder.binding.cardView1.setCardBackgroundColor(context.resources.getColor(
            when(quizList[position].second){
            "Jenkins" -> R.color.jenkins
            "Kubernetes" -> R.color.kubernetes
            "Ansible" -> R.color.gray
            "Docker" -> R.color.docker
            "Aws" -> R.color.aws
            "Git" -> R.color.git
            "Puppet" -> R.color.puppet
            "Chef" -> R.color.chef
            "Terraform" -> R.color.gray
            "CI/CD" -> R.color.cicd
            else -> R.color.other
        }))
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    inner class ViewHolder(val binding : TopHandsonReceipeRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}