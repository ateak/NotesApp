package ru.study.notesapp

import android.content.Context
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Екатерина Тимошкина on 16.07.2023
 */
class Presenter(var mainView: Contract.View?, var model: Contract.Model) : Contract.Presenter {

    fun showNotes(context: Context) {
        mainView?.initViews()
        StorageNotes.setDb(context)
        putDbDataToAdapter()
    }

    /**
     * Функция для обновления локального списка заметок данными из БД и передачи их в адаптер
     */
    private fun putDbDataToAdapter() {
        GlobalScope.launch(Dispatchers.Main) {
            StorageNotes.loadNotesFromDb().collect() {
                StorageNotes.allNotes.clear()
                StorageNotes.allNotes.addAll(it)
                mainView?.updateViews()
            }
        }
    }

    fun getNotes() = StorageNotes.allNotes


    override fun onButtonClick() {

    }
    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}