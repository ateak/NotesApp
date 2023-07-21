package ru.study.notesapp

import android.content.Context

/**
 *
 *
 * @author Имя Фамилия on 20.07.2023
 */
class DetailsNotePresenter(var context: Context, var detailsNoteView: Contract.DetailsNoteView?) : Contract.DetailsNotePresenter {

    private val storageNotes = StorageNotes(context)

}