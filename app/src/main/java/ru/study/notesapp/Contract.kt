package ru.study.notesapp

/**
 * Интерфейс для внедрения паттерна MVP
 * @author Екатерина Тимошкина on 16.07.2023
 */
interface Contract {
  
    interface MainView {
        fun showNotes(noteList: MutableList<Note>)
    }
    interface MainPresenter {
        fun deleteNote(id: Int?)
    }

    interface CreateNoteView
    interface CreateNotePresenter {
        fun saveNote(id: Int?, title: String, description: String)
    }
    interface DetailsNoteView

    interface DetailsNotePresenter
}
