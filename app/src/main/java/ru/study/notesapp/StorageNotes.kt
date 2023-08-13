package ru.study.notesapp

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Класс для работы с базой данных
 */
class StorageNotes(val context: Context) {
    private val db: MainDb by lazy { MainDb.getDb(context) }
    val allNotes: Flow<List<Note>>
        get() {
            return db.getDao().getAllNotes()
        }

    fun getNote(id: Int) = db.getDao().getNoteById(id)

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

    fun removeNote(id: Int) {
        GlobalScope.launch {
            db.getDao().deleteNoteById(id)
        }
    }
}
