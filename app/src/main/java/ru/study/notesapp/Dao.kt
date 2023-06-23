package ru.study.notesapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author Имя Фамилия on 23.06.2023
 */
@Dao
interface Dao {
    @Insert
    fun addNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>
}
