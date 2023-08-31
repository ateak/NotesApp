package ru.study.notesapp.domain.usecases

import ru.study.notesapp.domain.repository.NotesRepository

/**
 * Класс-прослойка для организации взаимодействия между viewmodel и data-слоем
 */
class DeleteNoteUseCase(private val notesRepository: NotesRepository) {

    fun execute(noteId: Int) {
        notesRepository.removeNote(noteId)
    }
}
