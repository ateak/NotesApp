package ru.study.notesapp.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель заметки для хранения данных внутри локального хранилища (data слой)
 */
@Entity(tableName = "notes")
data class NoteDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
)
