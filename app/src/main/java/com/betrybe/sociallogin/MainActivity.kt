package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val inputEmail: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val inputPassword: TextInputLayout by lazy {
        findViewById(R.id.password_text_input_layout)
    }
    private val loginButton: Button by lazy { findViewById(R.id.login_button) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEmail.editText?.doOnTextChanged { _, _, _, _ -> validateButton() }
        inputPassword.editText?.doOnTextChanged { _, _, _, _ -> validateButton() }
    }
    private fun validateButton() {
        val isEmailNotEmpty = inputEmail.editText?.text?.isNotBlank() == true
        val isPasswordNotEmpty = inputPassword.editText?.text?.isNotBlank() == true

        loginButton.isEnabled = isEmailNotEmpty && isPasswordNotEmpty
    }
}
