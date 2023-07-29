package ru.study.notesapp

/**
 * Интерфейс содержит функцию для обработки клика по заметке в NoteAdapter
 * @author Екатерина Тимошкина on 24.07.2023
 */
interface Listener {
    fun onNoteClick(note: Note)
}
