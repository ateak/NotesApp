package ru.study.notesapp

/**
 * Интерфейс, в котором содержатся события относительно подробного описания заметки - выбор заметки и обновление заметки
 */
interface DetailsNoteEvent

class SelectNoteEvent(val noteId: Int) : DetailsNoteEvent

class UpdateNoteEvent(val note: Note) : DetailsNoteEvent
