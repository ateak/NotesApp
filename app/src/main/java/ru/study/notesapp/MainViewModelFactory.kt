package ru.study.notesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 *
 * @author Имя Фамилия on 30.07.2023
 */
class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val storageNotes = StorageNotes(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(storageNotes) as T
    }
}