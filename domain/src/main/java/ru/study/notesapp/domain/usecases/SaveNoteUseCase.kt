package ru.study.notesapp.domain.usecases

import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Класс-прослойка для организации взаимодействия между viewmodel и data-слоем
 */
class SaveNoteUseCase(private val notesRepository: NotesRepository) {

    fun execute(note: Note) {
        notesRepository.addNote(note)
    }
}
