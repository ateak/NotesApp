package ru.study.notesapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.study.notesapp.CreateNoteViewModel
import ru.study.notesapp.DetailsNoteViewModel
import ru.study.notesapp.MainViewModel

/**
 * Модуль для для создания viewmodels
 */

val viewModelModule = module {

    viewModel {
        MainViewModel(notesRepository = get())
    }

    viewModel {
        CreateNoteViewModel(notesRepository = get())
    }

    viewModel {
        DetailsNoteViewModel(notesRepository = get())
    }
}
