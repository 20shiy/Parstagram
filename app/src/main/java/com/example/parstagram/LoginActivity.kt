package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener{
            val userName = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(userName, password)
        }

        findViewById<Button>(R.id.btn_signup).setOnClickListener{
            val userName = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpuser(userName, password)
        }
    }

    private fun loginUser(userName: String, password: String) {
        ParseUser.logInInBackground(userName, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
                // Hooray!  The user is logged in.
            } else {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()

            }})
        )
    }

    private fun signUpuser(userName: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(userName)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    companion object {
        const val TAG = "LoginActivity"
    }
}