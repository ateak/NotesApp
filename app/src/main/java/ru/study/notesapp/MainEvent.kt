package ru.study.notesapp

/**
 *  Интерфейс, в котором содержатся события, происходящие на главном экране - удаление заметки
 */
interface MainEvent

class DeleteNoteEvent(val id: Int) : MainEvent