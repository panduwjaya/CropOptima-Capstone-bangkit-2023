package com.cropoptima.cropoptima.main.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.auth.AuthActivity
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding
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
        Log.i("info", user.photoUrl.toString())


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
            .circleCrop()
            .into(binding.ivFotoProfil)
    }

    fun logout() {
        startActivity(Intent(binding.root.context, AuthActivity::class.java))
        Firebase.auth.signOut()
        activity?.finish()
        return
    }
}