package ru.study.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * MainViewModel для MainFragment
 */
class MainViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    private val _stateNoteList = MainState(storageNotes.allNotes)
    val stateNoteList = _stateNoteList

    fun handle(event: MainEvent) {
        when (event) {
            is DeleteNoteEvent -> {
                deleteNote(event.id)
            }
        }
    }

    private fun deleteNote(id: Int) {
        viewModelScope.launch {
            storageNotes.removeNote(id)
        }
    }
}
