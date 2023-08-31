package ru.study.notesapp.di

import org.koin.dsl.module
import ru.study.notesapp.domain.usecases.DeleteNoteUseCase
import ru.study.notesapp.domain.usecases.GetAllNotesUseCase
import ru.study.notesapp.domain.usecases.SaveNoteUseCase
import ru.study.notesapp.domain.usecases.SelectNoteUseCase
import ru.study.notesapp.domain.usecases.UpdateNoteUseCase

/**
 * Модуль для создания usecases
 */

val domainModule = module {

    factory { SaveNoteUseCase(notesRepository = get()) }

    factory { SelectNoteUseCase(notesRepository = get()) }

    factory { UpdateNoteUseCase(notesRepository = get()) }

    factory { DeleteNoteUseCase(notesRepository = get()) }

    factory { GetAllNotesUseCase(notesRepository = get()) }
}
