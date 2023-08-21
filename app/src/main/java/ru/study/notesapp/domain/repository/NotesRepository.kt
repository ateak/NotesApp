package ru.study.notesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.study.notesapp.domain.models.Note

/**
 *  Интерфейс содержит методы для работы с базой данных
 */
interface NotesRepository {

    fun getAllNotes(): Flow<List<Note>>

    fun getNote(id: Int) : Flow<Note>

    fun addNote(note: Note)

    fun updateNote(note: Note)

    fun removeNote(id: Int)
}
