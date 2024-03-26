package com.baben.apps.appformation3.presentation.screens.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.baben.apps.appformation3.core.app.AppConfig
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.databinding.ActivitySplashBinding
import com.baben.apps.appformation3.presentation.screens.home.HomeActivity
import com.baben.apps.appformation3.presentation.screens.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivities() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        delayedSplash()
    }

    private fun delayedSplash() {
        Handler(Looper.getMainLooper()).postDelayed(
            { this.displayNextScreen() },
            AppConfig.SPLASH_DELAY_MILLIS
        )
    }


    private fun displayNextScreen() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        val nextActivityIntent = if (isLoggedIn) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(nextActivityIntent)
        finish()
    }

}