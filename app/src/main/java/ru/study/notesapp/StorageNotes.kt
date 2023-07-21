package ru.study.notesapp

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Класс для работы с базой данных
 */
class StorageNotes(val context: Context) {
    private val db: MainDb by lazy { MainDb.getDb(context)  }
    val allNotes: MutableList<Note>
        get() = db.getDao().getAllNotes()

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

    fun removeNote(id: Int?) {
        GlobalScope.launch {
            db.getDao().deleteNote(id)
        }
    }

    //TODO так не работает, попробовать с Flow
    fun findNote(id: Int?) : Note? {
        var note: Note? = null
        GlobalScope.launch {
            note = db.getDao().findByPrimaryKey(id)
        }
        return note
    }
}

