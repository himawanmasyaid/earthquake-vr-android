package com.siklusdev.gempavr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.siklusdev.gempavr.R
import com.siklusdev.gempavr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_main_fragment)
//        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

//            val id = destination.id
//            val hideTollbar = id == R.id.navigation_home
//            binding.toolbar.isGone = hideTollbar

        }

    }
}