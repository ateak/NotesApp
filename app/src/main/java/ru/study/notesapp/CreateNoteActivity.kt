package ru.study.notesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_note_activity)
        initViews()
    }

    private fun initViews() {

        val title: EditText = findViewById(R.id.title)
        val description: EditText = findViewById(R.id.description)

        with(findViewById<Button>(R.id.button_create_note)) {
            this.setOnClickListener {
                StorageNotes.addNote(Note(title.text.toString(), description.text.toString()))
                finish()
            }
        }
    }

    companion object {
        fun newIntent(ctx: Context): Intent = Intent(ctx, CreateNoteActivity::class.java)
    }
}