package ru.study.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *
 *
 * @author Имя Фамилия on 23.06.2023
 */
@Database(entities = [Note::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context,
                MainDb::class.java,
                "`myNotes.db"
            ).build()
        }
    }
}