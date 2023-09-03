package ru.study.notesapp.di

import dagger.Module
import ru.study.notesapp.domain.repository.NotesRepository
import ru.study.notesapp.domain.usecases.DeleteNoteUseCase
import ru.study.notesapp.domain.usecases.GetAllNotesUseCase
import ru.study.notesapp.domain.usecases.SaveNoteUseCase
import ru.study.notesapp.domain.usecases.SelectNoteUseCase
import ru.study.notesapp.domain.usecases.UpdateNoteUseCase

/**
 * Модуль для создания usecases
 */

@Module
class DomainModule {

    fun provideSaveNoteUseCase(notesRepository: NotesRepository): SaveNoteUseCase {
        return SaveNoteUseCase(notesRepository = notesRepository)
    }

    fun provideSelectNoteUseCase(notesRepository: NotesRepository): SelectNoteUseCase {
        return SelectNoteUseCase(notesRepository = notesRepository)
    }

    fun provideUpdateNoteUseCase(notesRepository: NotesRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(notesRepository = notesRepository)
    }

    fun provideDeleteNoteUseCase(notesRepository: NotesRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(notesRepository = notesRepository)
    }

    fun provideGetAllNotesUseCase(notesRepository: NotesRepository): GetAllNotesUseCase {
        return GetAllNotesUseCase(notesRepository = notesRepository)
    }

}
