package ru.study.notesapp

/**
 * Интерфейс, в котором содержатся события необходимые для создания заметки - сохранение заметки
 */
interface CreateNoteEvent

class SaveNoteEvent(val note: Note) : CreateNoteEvent
