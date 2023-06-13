package ru.study.notesapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Адаптер для отрисовки элемента заметки в списке
 *
 */
class CustomRecyclerAdapter : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private val noteList = mutableListOf<Note>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.text_view_large)
        val smallTextView: TextView = itemView.findViewById(R.id.text_view_small)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.v("CustomRecyclerAdapter","onCreate view holder")

        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("CustomRecyclerAdapter", "on bind view holder заметка: ${noteList[position].title}")

        holder.largeTextView.text = noteList[position].title
        holder.smallTextView.text = noteList[position].description
    }

    /**
     * Функция для обновления и инициализации списка заметок
     */
    fun addData(data: MutableList<Note>) {
        noteList.clear()
        noteList.addAll(data)
        notifyDataSetChanged()
    }
}