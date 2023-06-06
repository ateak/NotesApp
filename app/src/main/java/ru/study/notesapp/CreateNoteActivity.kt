package ru.study.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_note_activity)

        val title: EditText = findViewById(R.id.title)
        val description: EditText = findViewById(R.id.description)

        val buttonCreateNote: Button = findViewById(R.id.button_create_note)
        buttonCreateNote.setOnClickListener {
            val note = Note(title.text.toString(), description.text.toString())
            StorageNotes.addNote(note)
            // val intent = Intent(this@CreateNoteActivity, MainActivity::class.java)
            // startActivity(intent)
            finish()
        }
    }
}