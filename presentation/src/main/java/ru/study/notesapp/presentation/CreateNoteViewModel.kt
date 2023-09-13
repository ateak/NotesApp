package ru.study.notesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.domain.usecases.SaveNoteUseCase

/**
 * CreateNoteViewModel для CreateNoteFragment
 */
class CreateNoteViewModel(private val saveNoteUseCase: SaveNoteUseCase) : ViewModel() {

    fun saveNote(note: Note) {
        viewModelScope.launch {
            saveNoteUseCase.execute(note)
        }
    }
}
