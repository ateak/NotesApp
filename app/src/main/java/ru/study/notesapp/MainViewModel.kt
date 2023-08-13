package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * MainViewModel для MainFragment
 */
class MainViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    val noteList = storageNotes.allNotes

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            storageNotes.removeNote(id)
        }
    }
}
