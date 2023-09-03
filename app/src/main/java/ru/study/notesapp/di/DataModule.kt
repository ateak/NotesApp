package ru.study.notesapp.di

import android.content.Context
import dagger.Module
import ru.study.notesapp.data.repository.NotesRepositoryImpl
import ru.study.notesapp.data.storage.roomdatabase.NoteStorage
import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Модуль для создания зависимостей, связанных с data-слоем
 */

@Module
class DataModule {

    fun provideNotesRepository(noteStorage: NoteStorage): NotesRepository {
        return NotesRepositoryImpl(noteStorage = noteStorage)
    }

    fun provideNotesStorage(context: Context): NoteStorage {
        return NoteStorage.getNoteStorage(context = context)
    }
}
