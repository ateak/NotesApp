package ru.study.notesapp

import android.content.Context

/**
 * DetailsNotePresenter для DetailsNoteFragment
 * @author Екатерина Тимошкина on 24.07.2023
 */
class DetailsNotePresenter(var context: Context, var detailsNoteView: Contract.DetailsNoteView?) :
    Contract.DetailsNotePresenter {

    private val storageNotes = StorageNotes(context)

    override fun updateNote(id: Int?, title: String, description: String) {
        storageNotes.updateNote(Note(id, title, description))
    }

    override fun getNote(noteId: Int) {
        val note = storageNotes.allNotes.find { it.id == noteId }
        detailsNoteView?.showNote(note!!)
    }
}
