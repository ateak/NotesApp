package ru.study.notesapp

import android.content.Context

/**
 * CreateNotePresenter для CreateNoteFragment
 * @author Екатерина Тимошкина on 20.07.2023
 */
class CreateNotePresenter(var context: Context, var createNoteView: Contract.CreateNoteView?) : Contract.CreateNotePresenter {

    private val storageNotes = StorageNotes(context)

    override fun saveNote(id: Int?, title: String, description: String) {
        storageNotes.addNote(Note(id, title, description))
    }
}