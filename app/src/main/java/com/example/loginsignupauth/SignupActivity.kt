package com.example.loginsignupauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupauth.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySignupBinding
    private  lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            val email = binding.signUpEmail.text.toString()
            val password = binding.signUpPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful){
                            Toast.makeText( this, "Signup Successful ", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText( this, "Signup Not Successful", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText( this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginText.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}