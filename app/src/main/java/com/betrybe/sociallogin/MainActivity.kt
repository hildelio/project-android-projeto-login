package com.betrybe.sociallogin

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

const val MIN_PASSWORD_LENGTH = 4
class MainActivity : AppCompatActivity() {

    private val inputEmail: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val inputPassword: TextInputLayout by lazy {
        findViewById(R.id.password_text_input_layout)
    }
    private val loginButton: Button by lazy { findViewById(R.id.login_button) }
    private val mainLayout: ConstraintLayout by lazy { findViewById(R.id.main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTextWatchers()

        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    private fun setupTextWatchers() {
        inputEmail.editText?.doOnTextChanged { _, _, _, _ -> validateButton() }
        inputPassword.editText?.doOnTextChanged { _, _, _, _ -> validateButton() }
    }

    private fun validateButton() {
        val isEmailNotEmpty = inputEmail.editText?.text?.isNotBlank() == true
        val isPasswordNotEmpty = inputPassword.editText?.text?.isNotBlank() == true
        loginButton.isEnabled = isEmailNotEmpty && isPasswordNotEmpty
    }

    private fun handleLogin() {
        val isEmailValid = isEmailValid(inputEmail.editText?.text)
        val passwordLength = inputPassword.editText?.text?.length

        validateEmail(isEmailValid)
        validatePassword(passwordLength)

        if (isEmailValid && passwordLength != null && passwordLength > MIN_PASSWORD_LENGTH) {
            successLogin()
        }
    }

    private fun validateEmail(isEmailValid: Boolean) {
        inputEmail.error = if (!isEmailValid) "Email inválido" else null
    }

    private fun validatePassword(passwordLength: Int?) {
        inputPassword.error = if (passwordLength != null && passwordLength <= MIN_PASSWORD_LENGTH) {
            "Senha deve ter mais de $MIN_PASSWORD_LENGTH caracteres"
        } else {
            null
        }
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()
    }

    private fun successLogin() {
        Snackbar.make(mainLayout, "Login efetuado com sucesso", Snackbar.LENGTH_LONG).show()
    }
}
