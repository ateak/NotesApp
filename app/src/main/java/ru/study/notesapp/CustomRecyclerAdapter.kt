package ru.study.notesapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.study.notesapp.databinding.RecyclerviewItemBinding

/**
 * Адаптер для отрисовки элемента заметки в списке
 */
class CustomRecyclerAdapter(val onClick: (Note) -> Unit) : RecyclerView.Adapter<CustomRecyclerAdapter.NoteHolder>() {

    private val noteList = mutableListOf<Note>()

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = RecyclerviewItemBinding.bind(itemView)
        fun bind(note: Note) = with(bindingAdapter) {
            textViewLarge.text = note.title
            textViewSmall.text = note.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        Log.v("CustomRecyclerAdapter", "onCreate view holder")

        return NoteHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        Log.v("CustomRecyclerAdapter", "on bind view holder заметка: ${noteList[position].title}")

        holder.bind(noteList[position])
        holder.itemView.setOnClickListener {
            onClick(noteList[position])
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    /**
     * Функция для обновления и инициализации списка заметок
     */
    fun addData(data: List<Note>) {
        noteList.clear()
        noteList.addAll(data)
        notifyDataSetChanged()
    }
}
