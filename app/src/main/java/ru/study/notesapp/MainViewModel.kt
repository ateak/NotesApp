package ru.study.notesapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 *
 * @author Имя Фамилия on 10.07.2023
 */
open class MainViewModel : ViewModel() {
    // val message: MutableLiveData<String> by lazy {
    //     MutableLiveData<String>()
    // }

    init {
        Log.e("AAA", "VM created")
    }

    override fun onCleared() {
        Log.e("AAA", "VM created")
        super.onCleared()
    }

    fun createNote() {
          // parentFragmentManager.beginTransaction()
          //               .replace(R.id.fragmentContainerView2, CreateNoteFragment.newInstance())
          //               .commit()
    }


}