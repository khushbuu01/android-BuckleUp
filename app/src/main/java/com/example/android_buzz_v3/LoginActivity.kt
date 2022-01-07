package com.example.android_buzz_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.back
import kotlinx.android.synthetic.main.activity_signup.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var lemail: EditText
    private lateinit var lpass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        lemail = findViewById(R.id.email_addre)
        lpass = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        reg.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        loginBtn.setOnClickListener{
            login()
        }

    }
    private fun login() {
        val email = lemail.text.toString()
        val pass = lpass.text.toString()
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
//                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, activity_dashboard::class.java))
                finish()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }


}