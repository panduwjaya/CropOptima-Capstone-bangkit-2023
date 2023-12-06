package com.cropoptima.cropoptima.auth.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.auth.login.LoginFragment
import com.cropoptima.cropoptima.databinding.FragmentSplashBinding
import com.cropoptima.cropoptima.main.MainActivity

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSplashBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_wellcomeFragment)
        }, 500)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}