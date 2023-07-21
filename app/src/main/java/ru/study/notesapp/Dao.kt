package ru.study.notesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс для определения методов доступа к базе данных
 */
@Dao
interface Dao {
    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNote(id: Int?)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): MutableList<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findByPrimaryKey(id: Int?): Note
}
