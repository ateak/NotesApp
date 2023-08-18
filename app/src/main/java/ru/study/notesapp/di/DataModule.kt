package ru.study.notesapp.di

import org.koin.dsl.module
import ru.study.notesapp.MainDb
import ru.study.notesapp.NotesRepository
import ru.study.notesapp.NotesRepositoryImpl

/**
 * Модуль для создания зависимостей, связанных с data-слоем
 */

val dataModule = module {

    single {
        MainDb.getDb(context = get())
    }

    single<NotesRepository> {
        NotesRepositoryImpl(mainDb = get())
    }
}
