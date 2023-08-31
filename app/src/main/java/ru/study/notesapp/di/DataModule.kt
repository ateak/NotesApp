package ru.study.notesapp.di

import org.koin.dsl.module
import ru.study.notesapp.data.repository.NotesRepositoryImpl
import ru.study.notesapp.data.storage.roomdatabase.NoteStorage
import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Модуль для создания зависимостей, связанных с data-слоем
 */

val dataModule = module {

    single {
        NoteStorage.getNoteStorage(context = get())
    }

    single<NotesRepository> {
        NotesRepositoryImpl(noteStorage = get())
    }
}
