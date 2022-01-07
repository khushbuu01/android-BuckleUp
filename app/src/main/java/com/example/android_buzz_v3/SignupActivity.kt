package com.example.android_buzz_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.back
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var remail: EditText
    private lateinit var rpassword: EditText
    private lateinit var rConfPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth
        remail=findViewById<EditText>(R.id.email)
        rpassword=findViewById<EditText>(R.id.passwordForS)
        rConfPass=findViewById<EditText>(R.id.conf_password)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        regBtn.setOnClickListener{
            signUpUser()
        }
        log.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun signUpUser() {
        if(remail.text.toString().isEmpty()) {
            remail.error = "Please enter an email"
            remail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(remail.text.toString()).matches()) {
            remail.error = "Please enter a valid email"
            remail.requestFocus()
            return
        }

        if(rpassword.text.toString().isEmpty()) {
            rpassword.error = "Please enter a password"
            rpassword.requestFocus()
            return
        }

        if (rpassword.text.toString() != rConfPass.text.toString()) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(remail.text.toString(), rpassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, task.exception.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}