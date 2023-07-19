package ru.study.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Класс для создания базы данных
 */
@Database(entities = [Note::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        //TODO найти способ не использовать allowMainThreadQueries()
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context,
                MainDb::class.java,
                "myNotes.db"
            ).allowMainThreadQueries().build()
        }
    }
}