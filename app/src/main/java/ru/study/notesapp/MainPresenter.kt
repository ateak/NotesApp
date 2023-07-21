package ru.study.notesapp

import android.content.Context

/**
 * MainPresenter для MainFragment
 * @author Екатерина Тимошкина on 19.07.2023
 */
class MainPresenter(var context: Context, var mainView: Contract.MainView?) :
    Contract.MainPresenter {

    private val storageNotes = StorageNotes(context)
    
    init {
        mainView?.showNotes(getNotes())
    }

    private fun getNotes() = storageNotes.allNotes

    override fun deleteNote(id: Int?) {
        storageNotes.removeNote(id)
    }
}
