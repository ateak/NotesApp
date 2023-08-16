package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * CreateNoteViewModel для CreateNoteFragment
 */
class CreateNoteViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    fun handle(event: CreateNoteEvent) {
        when (event) {
            is SaveNoteEvent -> {
                saveNote(event.note)
            }
        }
    }

    private fun saveNote(note: Note) {
        viewModelScope.launch {
            storageNotes.addNote(note)
        }
    }
}
