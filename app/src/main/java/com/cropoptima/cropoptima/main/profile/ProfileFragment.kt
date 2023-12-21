package com.cropoptima.cropoptima.main.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.auth.AuthActivity
import com.cropoptima.cropoptima.databinding.FragmentProfileBinding
import com.cropoptima.cropoptima.utils.Utils.getCurrentUserIdToken
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        auth = Firebase.auth
        user = auth.currentUser!!

        binding.tvHelp.setOnClickListener { openReadMeGithub() }
        binding.ivHelp.setOnClickListener { openReadMeGithub() }
        binding.ivLogout.setOnClickListener { logout() }
        binding.tvLogout.setOnClickListener { logout() }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNamaUser.text = user.displayName
        binding.tvEmailUser.text = user.email
        binding.tvIsVerified.text = "Verified status : ${ user.isEmailVerified}"
        Glide.with(this)
            .load(user.photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE )
            .skipMemoryCache(true)
            .transform(RoundedCorners(14))
            .circleCrop()
            .into(binding.ivFotoProfil)
        Glide.with(this)
            .load("https://picsum.photos/720/1024")
            .diskCacheStrategy(DiskCacheStrategy.NONE )
            .skipMemoryCache(true)
            .into(binding.ivBackground)
    }

    private fun openReadMeGithub(){
        val context = binding.root.context
        val url = "https://github.com/panduwjaya/CropOptima-Fullteam/tree/master"
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(context, Uri.parse(url))
    }

    fun logout() {
        startActivity(Intent(binding.root.context, AuthActivity::class.java))
        Firebase.auth.signOut()
        activity?.finish()
        return
    }
}