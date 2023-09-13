package ru.study.notesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.study.notesapp.domain.usecases.DeleteNoteUseCase
import ru.study.notesapp.domain.usecases.GetAllNotesUseCase

/**
 * MainViewModel для MainFragment
 */
class MainViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val noteList = getAllNotesUseCase.execute()

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.execute(id)
        }
    }
}
