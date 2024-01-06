package com.hungrydevops.app.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hungrydevops.app.databinding.ActivityHelpSupportBinding

class HelpSupportActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityHelpSupportBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.constraint2.setOnClickListener {
            shareWhatsapp()
        }
        binding.constraint3.setOnClickListener {
            val mobileNumber = "0000000000"
            val dialIntent = Intent(Intent(Intent.ACTION_DIAL))
            dialIntent.data = Uri.parse("tel:" + mobileNumber)
            startActivity(dialIntent)
        }
        binding.constraint4.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto","hungrydevops01@gmail.com",null))
            startActivity(Intent.createChooser(intent,"Send Email...."))
        }
    }
    fun shareWhatsapp() {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val uriString = "Type your Query"
            intent.putExtra(Intent.EXTRA_TEXT,uriString)
            intent.`package` = "com.whatsapp"
            startActivity(intent)
        }
        catch (e:Exception) {
            Toast.makeText(this@HelpSupportActivity,"Whatsapp not installed", Toast.LENGTH_SHORT).show()
        }
    }
}
