package ru.study.notesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.domain.usecases.SelectNoteUseCase
import ru.study.notesapp.domain.usecases.UpdateNoteUseCase

/**
 * DetailsNoteViewModel для DetailsNoteFragment
 */
class DetailsNoteViewModel(
    private val selectNoteUseCase: SelectNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    lateinit var note: Flow<Note>

    fun selectNote(noteId: Int) {
        note = selectNoteUseCase.execute(noteId)
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase.execute(note)
        }
    }
}
