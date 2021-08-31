package com.waqas.starshipapp.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waqas.starshipapp.R
import com.waqas.starshipapp.databinding.ActivitySplashBinding
import com.waqas.starshipapp.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presentNextActivity()
    }

    private fun goToMainActivity(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun presentNextActivity(){
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(1000)
            goToMainActivity()
        }
    }
}