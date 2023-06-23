package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.study.notesapp.databinding.ActivityDetailsNoteBinding

/**
 * Активити для просмотра подробного описания заметки
 */

class DetailsNoteActivity : AppCompatActivity() {

    private lateinit var bindingDetailsNote: ActivityDetailsNoteBinding
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDetailsNote = ActivityDetailsNoteBinding.inflate(layoutInflater)
        setContentView(bindingDetailsNote.root)
        initViews()
    }

    override fun onPause() {
        super.onPause()

        Log.v("DetailsNoteActivity", "DetailsNote activity onResume notes")
        note?.title = bindingDetailsNote.title.text.toString()
        note?.description = bindingDetailsNote.description.text.toString()
    }

    private fun initViews() {
        val hash = intent.getIntExtra("item_hash", 0)
        note = StorageNotes.allNotes.find { it.hashCode() == hash }
        bindingDetailsNote.title.setText(note?.title)
        bindingDetailsNote.description.setText(note?.description)
    }
}