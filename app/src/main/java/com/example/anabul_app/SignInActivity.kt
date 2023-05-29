package com.example.anabul_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.anabul_app.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set view binding
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // use to hide the action bar
        supportActionBar?.hide()

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener{
            if (checkAllField()){
                var email = binding.etEmail.text.toString()
                var password = binding.etPassword.text.toString()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Sign in successfully", Toast.LENGTH_SHORT).show()
                        // go to the next activity
                        var intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        // finish the current activity
                        finish()
                    }
                    else{
                        Log.e("Error", it.exception.toString())
                    }
                }
            }
        }

        binding.tvCreateAccount.setOnClickListener{
            // go to the next activity
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // finish the current activity
            finish()
        }

        binding.tvForgotPassword.setOnClickListener{
            // go to the next activity
            var intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            // finish the current activity
            finish()
        }
    }

    private fun checkAllField(): Boolean{
        var email = binding.etEmail.text.toString()
        if(binding.etEmail.text.toString() == ""){
            binding.textInputLayoutEmail.error = "Please enter your email"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Please enter a valid email"
            return false
        }
        if(binding.etPassword.text.toString() == ""){
            binding.textInputLayoutPassword.error = "Please enter your password"
            binding.textInputLayoutPassword.errorIconDrawable = null
            return false
        }
        if (binding.etPassword.length() <= 6){
            binding.textInputLayoutPassword.error = "Password must be at least 8 characters"
            return false
        }
        return true
    }
}