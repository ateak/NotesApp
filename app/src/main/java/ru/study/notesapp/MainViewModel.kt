package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * MainViewModel для MainFragment
 */
class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val noteList = notesRepository.getAllNotes()

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            notesRepository.removeNote(id)
        }
    }
}
