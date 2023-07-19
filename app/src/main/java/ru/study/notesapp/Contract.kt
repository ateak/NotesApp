package ru.study.notesapp

/**
 * Интерфейс для внедрения паттерна MVP
 * @author Екатерина Тимошкина on 16.07.2023
 */
interface Contract {
    interface View {
        fun showNotes(noteList: MutableList<Note>)
    }

    interface Presenter {
        fun deleteNote(id: Int?)
    }
}
