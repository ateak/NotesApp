package ru.study.notesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Класс для создания MainViewModel, CreateNoteViewModel, DetailsNoteViewModel
 */
class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val storageNotes = StorageNotes(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(storageNotes) as T
        modelClass.isAssignableFrom(CreateNoteViewModel::class.java) -> CreateNoteViewModel(storageNotes) as T
        modelClass.isAssignableFrom(DetailsNoteViewModel::class.java) -> DetailsNoteViewModel(storageNotes) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}
