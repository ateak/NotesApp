package ru.study.notesapp.presentation.listeners

import ru.study.notesapp.domain.models.Note

/**
 * Интерфейс содержит функцию для обработки клика по заметке в NoteAdapter
 */
interface Listener {
    fun onNoteClick(note: Note)
}
