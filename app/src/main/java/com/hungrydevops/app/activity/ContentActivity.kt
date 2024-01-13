package com.hungrydevops.app.activity

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityContentBinding
import com.rajat.pdfviewer.HeaderData
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo

class ContentActivity : BaseActivity() {

    val binding by lazy {
        ActivityContentBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvHeading.text= intent.getStringExtra("value")?.trim() ?:""

        binding.imgBack.setOnClickListener{finish()}

        getURL()


    }

    private fun getURL() {
        val collection= intent.getStringExtra("collection")?.trim()?:""
        val value= intent.getStringExtra("value")?.trim()?:""
        val db = Firebase.firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        showLoading()
        db.collection(collection.trim()).document(value.trim()).get()
            .addOnSuccessListener {
                try {
//                    val description = it.get("description").toString()
//                    binding.tvDesc.text= Html.fromHtml(description)
                    val pdfURL=it.get("pdfURL").toString()
                    displayPDF(pdfURL)
                } catch (e:Exception){
                    Toast.makeText(this@ContentActivity,"Some error found",Toast.LENGTH_SHORT).show()}
                hideLoading()
            }
            .addOnFailureListener { exception ->
                hideLoading()
                Toast.makeText(this@ContentActivity, "No Internet", Toast.LENGTH_SHORT).show()
            }
        }

    private fun displayPDF(pdfURL: String) {
        val  storage= FirebaseStorage.getInstance()
        val url=storage.getReferenceFromUrl(pdfURL)

        url.downloadUrl.addOnSuccessListener {
            binding.pdfView.initWithUrl(it.toString(), HeaderData() ,lifecycleScope,lifecycle)
        }
    }
}