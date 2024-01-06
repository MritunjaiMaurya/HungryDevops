package com.hungrydevops.app.activity

import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityAboutUsBinding

class AboutUsActivity : BaseActivity() {

    val binding by lazy {
        ActivityAboutUsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setSingleClickListener{
            finish()
        }

        binding.tvHeading.text=intent.getStringExtra("value")

        getContent()


    }

    private fun getContent(){
        showLoading()
        val db= Firebase.firestore
        db.firestoreSettings= FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build()

        intent.getStringExtra("value")?.let {
            db.collection("hungrydevopscms").document(it).get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.exists()) {
                        val data = querySnapshot.data
                        if (data != null) {
                            binding.tvDesc.text = Html.fromHtml(data.get("content").toString())
                        }
                    }
                    hideLoading()
                }
                .addOnFailureListener {
                    hideLoading()
                    Toast.makeText(this,"No Internet", Toast.LENGTH_SHORT).show() }
        }
    }
}
