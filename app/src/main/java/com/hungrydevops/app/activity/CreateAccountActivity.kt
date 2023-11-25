package com.hungrydevops.app.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : BaseActivity() {

    val binding by lazy{
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val items = listOf("Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand",
            "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu",
            "Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal")
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1,items)
        binding.edtCountry.setAdapter(adapter)

        binding.edtCountry.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, p, l ->
            val itemSelected = adapterView.getItemAtPosition(p)
            Toast.makeText(this,"$itemSelected", Toast.LENGTH_SHORT).show()
        }

    }
}