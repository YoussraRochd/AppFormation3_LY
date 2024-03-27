package com.baben.apps.appformation3.presentation.screens.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.databinding.ActivityLoginBinding
import com.baben.apps.appformation3.presentation.screens.home.HomeActivity
import com.baben.apps.appformation3.presentation.screens.signup.SignupActivity
import androidx.lifecycle.ViewModel

class LoginActivity : BaseActivities() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActions()
    }

    private fun setupActions() {
        binding.uiLoginButton.setOnClickListener(::onLoginButtonClicked)
        binding.uiSignupButton.setOnClickListener(::onSignupButtonClicked)
    }

    private fun onLoginButtonClicked(view: View?) {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        // Appel de la méthode de connexion dans le ViewModel
        loginViewModel.login(username, password)
        startActivity(Intent(context, HomeActivity::class.java))
        finish()
    }

    private fun onSignupButtonClicked(view: View?) {
        startActivity(Intent(context, SignupActivity::class.java))
    }

}