package ru.study.notesapp

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Активити для просмотра подробного описания заметки
 */

class DetailsNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detailed_note)
        initViews()
    }

    private fun initViews() {

        val title: EditText = findViewById(R.id.title)
        val description: EditText = findViewById(R.id.description)

        val hash = intent.getIntExtra("item_hash", 0)
        val note = StorageNotes.allNotes.find { it.hashCode() == hash }
        title.setText(note?.title)
        description.setText(note?.description)

        // другой способ через Serializable
        // val item = intent.getSerializableExtra("item") as Note
        // title.setText(item.title)
        // description.setText(item.description)
    }
}