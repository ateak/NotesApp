package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * DetailsNoteViewModel для DetailsNoteFragment
 */
class DetailsNoteViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    private val _stateNote = MutableStateFlow(DetailsNoteState(Note(0, "", "")))
    val stateNote = _stateNote

    fun handle(event: DetailsNoteEvent) {
        when (event) {
            is SelectNoteEvent -> {
                selectNote(event.noteId)
            }

            is UpdateNoteEvent -> {
                updateNote(event.note)
            }
        }
    }

    private fun selectNote(noteId: Int) {
        viewModelScope.launch {
            storageNotes.getNote(noteId).collect {
                stateNote.value = DetailsNoteState(note = it)
            }
        }
    }

    private fun updateNote(note: Note) {
        storageNotes.updateNote(note)
    }
}
