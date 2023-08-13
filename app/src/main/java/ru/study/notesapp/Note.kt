package ru.study.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Схема таблицы для базы данных
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
)
