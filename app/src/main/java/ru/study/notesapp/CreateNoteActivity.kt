package ru.study.notesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.study.notesapp.databinding.ActivityCreateNoteBinding

/**
 * Активити для создания заметки
 */

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var bindingCreateNote: ActivityCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingCreateNote = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(bindingCreateNote.root)
        initViews()
    }

    private fun initViews() {
        val db = MainDb.getDb(this)
        with(bindingCreateNote.buttonCreateNote) {
            this.setOnClickListener {
                val note = Note(
                    null,
                    bindingCreateNote.title.text.toString(),
                    bindingCreateNote.description.text.toString()
                )
                Thread {
                    db.getDao().addNote(note)
                }.start()
                finish()
            }
        }
    }

    companion object {
        fun newIntent(ctx: Context): Intent = Intent(ctx, CreateNoteActivity::class.java)
    }
}