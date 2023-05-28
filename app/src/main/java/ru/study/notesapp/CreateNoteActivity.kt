package ru.study.notesapp

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle

class CreateNoteActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.create_note_activity)
    }
}