package ru.study.notesapp.data.storage.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.study.notesapp.data.storage.models.NoteDb

/**
 * Интерфейс для определения методов доступа к базе данных
 */
@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun addNote(noteDb: NoteDb)

    @Update
    fun updateNote(noteDb: NoteDb)

    @Delete
    fun deleteNote(noteDb: NoteDb)

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteNoteById(noteId: Int?)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteDb>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int?): Flow<NoteDb>
}
