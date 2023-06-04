package ru.study.notesapp

object StorageNotes {
    val allNotes = mutableListOf<Note>()

    fun addNote(note: Note) {
        allNotes.add(note)
    }

    fun removeNote(note: Note) {
        allNotes.remove(note)
    }
}
