package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.hungrydevops.app.Adapter.TopicWiseAdapter
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityChooseTopicBinding

class ChooseTopicActivity : BaseActivity() {

    val binding by lazy{
        ActivityChooseTopicBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setSingleClickListener {
            onBackPressed()
        }

        binding.rvTopicCategory.layoutManager=GridLayoutManager(this,2)
        val adapter=TopicWiseAdapter(this)
        binding.rvTopicCategory.adapter=adapter

    }
}