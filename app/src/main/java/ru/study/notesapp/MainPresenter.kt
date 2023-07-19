package ru.study.notesapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Екатерина Тимошкина on 16.07.2023
 */
class MainPresenter(var mainView: Contract.View?, var model: Contract.Model) : Contract.Presenter {

    fun showNotes(context: Context) {
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

    fun deleteItem(viewHolder: RecyclerView.ViewHolder) {
        mainView?.deleteNote(viewHolder)
    }
    fun getNotes() = StorageNotes.allNotes


    override fun onButtonClick() {
    }
}