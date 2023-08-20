package ru.study.notesapp

/**
 * Интерфейс содержит функцию для обработки клика по заметке в NoteAdapter
 */
interface Listener {
    fun onNoteClick(note: Note)
}
