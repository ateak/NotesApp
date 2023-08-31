package ru.study.notesapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Класс-прослойка для организации взаимодействия между viewmodel и data-слоем
 */
class GetAllNotesUseCase(private val notesRepository: NotesRepository) {

    fun execute(): Flow<List<Note>> {
        return notesRepository.getAllNotes()
    }
}
