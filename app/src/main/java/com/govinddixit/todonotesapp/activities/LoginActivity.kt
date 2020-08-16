package com.govinddixit.todonotesapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.govinddixit.todonotesapp.constants.AppConstant
import com.govinddixit.todonotesapp.constants.PrefConstant
import com.govinddixit.todonotesapp.R

class LoginActivity : AppCompatActivity() {

    lateinit var editTextFullName: EditText
    lateinit var editTextUserName: EditText
    lateinit var buttonLogin: Button

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
    }

    private fun bindViews() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName = findViewById(R.id.editTextUserName)
        buttonLogin = findViewById(R.id.buttonLogin)

        val clickAction = View.OnClickListener {
            val fullName = editTextFullName.text.toString()
            val userName = editTextUserName.text.toString()
            if (fullName.isNotEmpty() && userName.isNotEmpty()) {
                val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                intent.putExtra(AppConstant.FULL_NAME, fullName)
                startActivity(intent)
                saveLoginStatus()
                saveFullName(fullName)
            } else {
                Toast.makeText(this@LoginActivity, "full name and user name should not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        buttonLogin.setOnClickListener(clickAction)
    }

    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstant.FULL_NAME, fullName)
        editor.apply()
    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN, true)
        editor.apply()
    }

}