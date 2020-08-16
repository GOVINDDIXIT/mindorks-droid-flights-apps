package com.govinddixit.todonotesapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.govinddixit.todonotesapp.constants.AppConstant
import com.govinddixit.todonotesapp.constants.PrefConstant
import com.govinddixit.todonotesapp.R
import com.govinddixit.todonotesapp.adapter.NotesAdapter
import com.govinddixit.todonotesapp.clicklisteners.ItemClickListeners
import com.govinddixit.todonotesapp.model.Notes
import java.util.*

class MyNotesActivity : AppCompatActivity() {

    var fullName = ""
    lateinit var fabAddNotes: FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    val TAG = "MyNotesActivity"
    lateinit var recyclerViewNotes: RecyclerView
    var notesList = ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)

        bindViews()
        setSharedPreferences()
        getIntentData()

        supportActionBar?.title = fullName

        fabAddNotes.setOnClickListener { initDialogBox() }
    }

    private fun initDialogBox() {
        val view = LayoutInflater.from(this).inflate(R.layout.item_add_dialog_box, null)
        val editTextTitle = view.findViewById<EditText>(R.id.etTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.etDescreption)
        val buttonSubmit = view.findViewById<Button>(R.id.btnSubmit)

        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()

        buttonSubmit.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                val notes = Notes(title, description)
                notesList.add(notes)
            } else {
                Toast.makeText(this@MyNotesActivity, "Please enter title and description", Toast.LENGTH_SHORT).show()
            }
            setupRecyclerView()
            dialog.hide()
        }
        dialog.show()
    }

    private fun setupRecyclerView() {
        val itemClickListeners = object : ItemClickListeners {
            override fun onClick(notes: Notes?) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(AppConstant.TITLE, notes?.title)
                intent.putExtra(AppConstant.DESCRIPTION, notes?.description)
                startActivity(intent)
            }
        }
        val notesAdapter = NotesAdapter(notesList, itemClickListeners)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewNotes.layoutManager = linearLayoutManager
        recyclerViewNotes.adapter = notesAdapter
    }

    private fun bindViews() {
        fabAddNotes = findViewById(R.id.fabAddNotes)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
    }

    private fun setSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun getIntentData() {
        val intent = intent
        if(intent.hasExtra(AppConstant.FULL_NAME)){
            fullName = intent.getStringExtra(AppConstant.FULL_NAME)!!
        }
        if (fullName.isEmpty()) {
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "")!!
        }
    }
}