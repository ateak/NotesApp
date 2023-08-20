package ru.study.notesapp

import kotlinx.coroutines.flow.Flow

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
