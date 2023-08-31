package ru.study.notesapp.data.storage.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.study.notesapp.data.storage.models.NoteDb

/**
 * Создание базы данных для хранения заметок
 */
@Database(entities = [NoteDb::class], version = 1)
abstract class NoteStorage : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        fun getNoteStorage(context: Context): NoteStorage {
            return Room.databaseBuilder(
                context,
                NoteStorage::class.java,
                "myNotes.db"
            ).build()
        }
    }
}
