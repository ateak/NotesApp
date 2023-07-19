package ru.study.notesapp

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/**
 * Интерфейс для внедрения паттерна MVP
 * @author Екатерина Тимошкина on 16.07.2023
 */
interface Contract {
    interface View {
        //метод для отображения списка заметок
        fun initViews()

        fun updateViews()

        fun deleteNote(viewHolder: RecyclerView.ViewHolder)

        fun openFragment(fragment: Fragment, idHolder: Int)
    }

    interface Model {
       // fun getData()
    }

    interface Presenter {

        fun onButtonClick()

    }
}
