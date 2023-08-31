package ru.study.notesapp.domain.models

/**
 * Модель заметки для передачи данных внутри domain и presentation слоя
 */
data class Note(
    var id: Int,
    var title: String,
    var description: String,
)
