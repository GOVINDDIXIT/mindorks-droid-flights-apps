package com.govinddixit.todonotesapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.govinddixit.todonotesapp.constants.AppConstant
import com.govinddixit.todonotesapp.R

class DetailActivity : AppCompatActivity() {
    val TAG = "DetailActivity"
    lateinit var textViewTitle: TextView
    lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindViews()
        intentData()
    }

    private fun intentData() {
        val intent = intent
        val title = intent.getStringExtra(AppConstant.TITLE)
        val description = intent.getStringExtra(AppConstant.DESCRIPTION)

        textViewTitle.text = title
        textViewDescription.text = description

    }

    private fun bindViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
    }
}