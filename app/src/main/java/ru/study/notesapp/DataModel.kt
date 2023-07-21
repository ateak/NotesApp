package ru.study.notesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Класс для передачи сообщений между фрагментами
 * @author Екатерина Тимошкина on 16.07.2023
 */
open class DataModel : ViewModel() {
    val noteId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}