package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Активити для просмотра подробного описания заметки
 */

class DetailsNoteActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private var note : Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detailed_note)
        initViews()
    }

    override fun onPause() {
        super.onPause()

        Log.v("DetailsNoteActivity","DetailsNote activity onResume notes")
        note?.title = titleEditText.text.toString()
        note?.description = descriptionEditText.text.toString()
       // finish()
    }

    private fun initViews() {

        titleEditText = findViewById(R.id.title)
        descriptionEditText = findViewById(R.id.description)

        val hash = intent.getIntExtra("item_hash", 0)
        note = StorageNotes.allNotes.find { it.hashCode() == hash }
        titleEditText.setText(note?.title)
        descriptionEditText.setText(note?.description)

        // другой способ через Serializable
        // val item = intent.getSerializableExtra("item") as Note
        // title.setText(item.title)
        // description.setText(item.description)
    }
}