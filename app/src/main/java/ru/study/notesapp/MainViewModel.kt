package ru.study.notesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 *
 * @author Имя Фамилия on 29.07.2023
 */
class MainViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    val noteList = MutableLiveData(getNotes())

    val note: MutableLiveData<Note> by lazy {
        MutableLiveData<Note>()
    }

    private fun getNotes() = storageNotes.allNotes

    fun saveNote(id: Int?, title: String, description: String) {
        storageNotes.addNote(Note(id, title, description))
       // noteList.value = getNotes()
    }

    fun deleteNote(id: Int?) {
        storageNotes.removeNote(id)
    }

    fun updateNote(id: Int?, title: String, description: String) {
        storageNotes.updateNote(Note(id, title, description))
        //noteList.value = getNotes()
    }

    fun editNote(noteEdit: Note) {
        note.value = storageNotes.allNotes.find { it.id == noteEdit.id }
    }

    fun updateNoteList() {
        noteList.postValue(getNotes())
    }
}
