package com.siklusdev.gempavr.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siklusdev.gempavr.databinding.ActivitySplashScreenBinding
import com.siklusdev.gempavr.ui.MainActivity
import kotlinx.coroutines.*

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            delay(1500)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}