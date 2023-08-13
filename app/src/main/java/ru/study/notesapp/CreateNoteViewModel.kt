package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * CreateNoteViewModel для CreateNoteFragment
 */
class CreateNoteViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    fun saveNote(id: Int, title: String, description: String) {
        viewModelScope.launch {
            storageNotes.addNote(Note(id, title, description))
        }
    }
}
