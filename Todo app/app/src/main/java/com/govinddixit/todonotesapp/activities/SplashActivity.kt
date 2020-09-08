package com.govinddixit.todonotesapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.govinddixit.todonotesapp.R
import com.govinddixit.todonotesapp.constants.PrefConstant

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSharedPreferences()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN, false)

        val handler = Handler()
        handler.postDelayed({
            if (isLoggedIn) {
                goToNotesActivity()
            } else {
                goToLoginActivity()
            }
        }, 2000)
    }

    private fun goToNotesActivity() {
        val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
        startActivity(intent)
    }

    private fun goToLoginActivity() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun setSharedPreferences() {
        sharedPreferences = getSharedPreferences(
            PrefConstant.SHARED_PREFERENCES_NAME, MODE_PRIVATE
        )
    }
}