package ru.study.notesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 *
 * @author Имя Фамилия on 29.07.2023
 */
class MainViewModel(private val storageNotes: StorageNotes) : ViewModel() {

    val noteList: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val note: MutableLiveData<Note> by lazy {
        MutableLiveData<Note>()
    }

    init {
        noteList.value = getNotes()
    }

    private fun getNotes() = storageNotes.allNotes

    fun saveNote(id: Int?, title: String, description: String) {
        storageNotes.addNote(Note(id, title, description))
        noteList.value = getNotes()
    }

    fun deleteNote(id: Int?) {
        storageNotes.removeNote(id)
        noteList.value = getNotes()
    }

    fun updateNote(id: Int?, title: String, description: String) {
        storageNotes.updateNote(Note(id, title, description))
        noteList.value = getNotes()
    }
}
