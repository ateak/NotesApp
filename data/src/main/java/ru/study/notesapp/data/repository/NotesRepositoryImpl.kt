package ru.study.notesapp.data.repository

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.study.notesapp.data.storage.models.NoteDb
import ru.study.notesapp.data.storage.roomdatabase.NoteStorage
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Реализация интерфейса NotesRepository (Room + SQLite)
 */
class NotesRepositoryImpl(private val noteStorage: NoteStorage) : NotesRepository {

    override fun getAllNotes(): Flow<List<Note>> {

        return noteStorage.getNoteDao().getAllNotes().map { noteList ->
            noteList.map { Note(it.id, it.title, it.description) }
        }
    }

    override fun getNote(id: Int): Flow<Note> {

        return noteStorage.getNoteDao().getNoteById(id).map {
            Note(it.id, it.title, it.description)
        }
    }

    override fun addNote(note: Note) {
        val noteDb = NoteDb(note.id, note.title, note.description)

        GlobalScope.launch {
            if (noteDb.title != "" || noteDb.description != "") {
                noteStorage.getNoteDao().addNote(noteDb)
            }
        }
    }

    override fun updateNote(note: Note) {
        val noteDb = NoteDb(note.id, note.title, note.description)

        GlobalScope.launch {
            if (noteDb.title != "" || noteDb.description != "") {
                noteStorage.getNoteDao().updateNote(noteDb)
            } else {
                noteStorage.getNoteDao().deleteNote(noteDb)
            }
        }
    }

    override fun removeNote(id: Int) {
        GlobalScope.launch {
            noteStorage.getNoteDao().deleteNoteById(id)
        }
    }
}
