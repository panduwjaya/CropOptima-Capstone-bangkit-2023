package com.cropoptima.cropoptima.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.auth.AuthActivity
import com.cropoptima.cropoptima.databinding.ActivityMainBinding
import com.cropoptima.cropoptima.utils.Utils

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest

class
MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationViewHome
        val navController = findNavController(R.id.nav_host_fragment_home)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home, R.id.detection, R.id.profile
            )
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val user = Firebase.auth.currentUser
        Log.i("info", Utils.getCurrentUserIdToken())
        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

        if (user == null) {
            startActivity(Intent(binding.root.context, AuthActivity::class.java))
        }
    }


}
