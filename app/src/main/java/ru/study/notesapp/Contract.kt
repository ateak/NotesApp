package ru.study.notesapp

import androidx.fragment.app.Fragment

/**
 * Интерфейс для внедрения паттерна MVP
 * @author Екатерина Тимошкина on 16.07.2023
 */
interface Contract {
    interface View {
        //метод для отображения списка заметок
        fun initViews()

        fun updateViews()

        fun openFragment(fragment: Fragment, idHolder: Int)
    }

    interface Model {
       // fun getData()
    }

    interface Presenter {

        fun onButtonClick()

        fun onViewCreated()

        fun onDestroy()
    }
}
