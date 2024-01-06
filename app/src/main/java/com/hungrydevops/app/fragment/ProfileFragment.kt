package com.hungrydevops.app.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.hungrydevops.app.R
import com.hungrydevops.app.activity.AboutUsActivity
import com.hungrydevops.app.activity.HelpSupportActivity
import com.hungrydevops.app.activity.OnboardingActivity
import com.hungrydevops.app.activity.ProfileInfoActivity
import com.hungrydevops.app.base.BaseFragment
import com.hungrydevops.app.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()


        binding.tvName.text = getSharedPreferences("full_name")
        binding.tvEmail.text = getSharedPreferences("email")

        binding.imgAvatar.setImageResource(
            when (getSharedPreferences("img")) {
                "1" -> R.drawable.avatar_1
                "2" -> R.drawable.avatar_2
                "3" -> R.drawable.avatar_3
                "4" -> R.drawable.avatar_4
                "5" -> R.drawable.avatar_5
                "6" -> R.drawable.avatar_6
                "7" -> R.drawable.avatar_7
                "8" -> R.drawable.avatar_8
                else -> R.drawable.avatar_1
            }
        )

        binding.c1.setOnClickListener {
            startActivity(Intent(context, ProfileInfoActivity::class.java))
        }

        binding.c2.setOnClickListener {
            startActivity(Intent(context, HelpSupportActivity::class.java))
        }
        binding.c3.setOnClickListener {
            val intent = Intent(context, AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.c4.setOnClickListener {
            shareAppWithFriends()
        }
        binding.c5.setOnClickListener {
            openPlayStoreRating()
        }
        binding.c6.setOnClickListener {
            showBottomSheet()
        }

        return binding.root
    }

    private fun shareAppWithFriends() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this great app!")
            val appLink = "https://play.google.com/store/apps/details?id=com.androidprotoolkit.com"
            putExtra(
                Intent.EXTRA_TEXT,
                "I've been using this awesome app and thought you might like it. Check it out here: $appLink"
            )
        }
        val chooser = Intent.createChooser(shareIntent, "Share App via....")
        startActivity(chooser)
    }

    private fun openPlayStoreRating() {
        val appPackageName = "com.hungrydevops.app"
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )

        } catch (e: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_logout, null)
        bottomSheetDialog.setContentView(view)

        val goToLogin = view.findViewById<MaterialButton>(R.id.b4)
        val cancel = view.findViewById<MaterialButton>(R.id.b2)
        goToLogin.setOnClickListener {
            logoutUser()
        }
        cancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    private fun logoutUser() {
        auth.signOut()

        // Sign out from Google Sign-In
        val googleSignInClient =
            GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN)
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
        }
        val sp = requireActivity().getSharedPreferences("Data", Context.MODE_PRIVATE)
        sp.edit().clear().apply()
        startActivity(Intent(context, OnboardingActivity::class.java))
        requireActivity().finish()
    }

    override fun onResume() {
        super.onResume()
        binding.imgAvatar.setImageResource(
            when (getSharedPreferences("img")) {
                "1" -> R.drawable.avatar_1
                "2" -> R.drawable.avatar_2
                "3" -> R.drawable.avatar_3
                "4" -> R.drawable.avatar_4
                "5" -> R.drawable.avatar_5
                "6" -> R.drawable.avatar_6
                "7" -> R.drawable.avatar_7
                "8" -> R.drawable.avatar_8
                else -> R.drawable.avatar_1
            }
        )
    }
}