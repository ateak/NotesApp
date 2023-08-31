package ru.study.notesapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.study.notesapp.presentation.CreateNoteViewModel
import ru.study.notesapp.presentation.DetailsNoteViewModel
import ru.study.notesapp.presentation.MainViewModel

/**
 * Модуль для для создания viewmodels
 */

val viewModelModule = module {

    viewModel {
        MainViewModel(getAllNotesUseCase = get(), deleteNoteUseCase = get())
    }

    viewModel {
        CreateNoteViewModel(saveNoteUseCase = get())
    }

    viewModel {
        DetailsNoteViewModel(selectNoteUseCase = get(), updateNoteUseCase = get())
    }
}
