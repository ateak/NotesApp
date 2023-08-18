package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * DetailsNoteViewModel для DetailsNoteFragment
 */
class DetailsNoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    lateinit var note: Flow<Note>

    fun selectNote(noteId: Int) {
        note = notesRepository.getNote(noteId)
    }

    fun updateNote(id: Int, title: String, description: String) {
        viewModelScope.launch {
            notesRepository.updateNote(Note(id, title, description))
        }
    }
}
