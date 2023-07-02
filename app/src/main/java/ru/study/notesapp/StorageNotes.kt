package ru.study.notesapp

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Синглтон для хранения списка заметок из базы данных и обращения к ней через методы интерфейса Dao
 */
object StorageNotes {
    private lateinit var db: MainDb
    var allNotes: MutableList<Note> = mutableListOf()

    fun setDb(context: Context) {
        db = MainDb.getDb(context)
    }

    fun loadNotesFromDb(): Flow<List<Note>> = db.getDao().getAllNotes()

    fun addNote(note: Note) {
        GlobalScope.launch {
            if (note.title != "" || note.description != "") {
                db.getDao().addNote(note)
            }
        }
    }

    fun updateNote(note: Note) {
        GlobalScope.launch {
            if (note.title != "" || note.description != "") {
                db.getDao().updateNote(note)
            } else {
                db.getDao().deleteNote(note)
            }
        }
    }

    fun removeNote(note: Note) {
        GlobalScope.launch {
            db.getDao().deleteNote(note)
        }
    }
}

