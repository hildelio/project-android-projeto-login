package com.betrybe.sociallogin

import android.os.Bundle
import android.util.Patterns
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

        loginButton.setOnClickListener {
            val isEmailValid = isEmailValid(inputEmail.editText?.text)
            val passwordLength = inputPassword.editText?.text?.length
            if (!isEmailValid) {
                inputEmail.error = "Email inválido"
            } else {
                inputEmail.error = null
            }
            if (passwordLength != null) {
                if (passwordLength <= 4) {
                    inputPassword.error = "Senha deve ter mais de 4 caracteres"
                } else {
                    inputPassword.error = null
                }
            }
        }
    }
    private fun validateButton() {
        val isEmailNotEmpty = inputEmail.editText?.text?.isNotBlank() == true
        val isPasswordNotEmpty = inputPassword.editText?.text?.isNotBlank() == true

        loginButton.isEnabled = isEmailNotEmpty && isPasswordNotEmpty
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()
    }
}
