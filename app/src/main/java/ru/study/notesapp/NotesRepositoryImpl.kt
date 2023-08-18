package ru.study.notesapp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Реализация интерфейса NotesRepository (Room + SQLite)
 */
class NotesRepositoryImpl(private val mainDb: MainDb) : NotesRepository {

    override fun getAllNotes() = mainDb.getDao().getAllNotes()

    override fun getNote(id: Int) = mainDb.getDao().getNoteById(id)

    override fun addNote(note: Note) {
        GlobalScope.launch {
            if (note.title != "" || note.description != "") {
                mainDb.getDao().addNote(note)
            }
        }
    }

    override fun updateNote(note: Note) {
        GlobalScope.launch {
            if (note.title != "" || note.description != "") {
                mainDb.getDao().updateNote(note)
            } else {
                mainDb.getDao().deleteNote(note)
            }
        }
    }

    override fun removeNote(id: Int) {
        GlobalScope.launch {
            mainDb.getDao().deleteNoteById(id)
        }
    }
}
