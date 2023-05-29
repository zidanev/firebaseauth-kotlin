package com.example.anabul_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import android.widget.Toast
import com.example.anabul_app.databinding.ActivitySignUpBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set view binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener{
            if (checkAllField()){
                var email = binding.etEmail.text.toString()
                var password = binding.etPassword.text.toString()
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        auth.signOut()
                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                        // go to the next activity
                        var intent = Intent(this, SignInActivity::class.java)
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

        binding.tvAlreadyHaveAnAccount.setOnClickListener{
            // go to the next activity
            var intent = Intent(this, SignInActivity::class.java)
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
            binding.textInputLayoutConfirmPassword.errorIconDrawable = null
            return false
        }
        if (binding.etPassword.length() <= 6){
            binding.textInputLayoutPassword.error = "Password must be at least 8 characters"
            binding.textInputLayoutConfirmPassword.errorIconDrawable = null
            return false
        }
        if(binding.etConfirmPassword.text.toString() == ""){
            binding.textInputLayoutConfirmPassword.error = "Please enter your password"
            binding.textInputLayoutConfirmPassword.errorIconDrawable = null
            return false
        }
        if(binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()){
            binding.textInputLayoutConfirmPassword.error = "Password does not match"
            return false
        }
        return true
    }
}