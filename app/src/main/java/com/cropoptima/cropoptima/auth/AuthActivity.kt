package com.cropoptima.cropoptima.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cropoptima.cropoptima.R
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.cropoptima.cropoptima.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_auth_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(binding.toolbarAuth)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }
}