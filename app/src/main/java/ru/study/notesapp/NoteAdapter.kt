package ru.study.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.study.notesapp.databinding.RecyclerviewItemBinding

/**
 * Адаптер для отрисовки элемента заметки в списке
 */
class NoteAdapter(val listener: Listener): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private val noteList = mutableListOf<Note>()

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = RecyclerviewItemBinding.bind(itemView)
        fun bind(note: Note, listener: Listener) = with(bindingAdapter) {
            textViewLarge.text = note.title
            textViewSmall.text = note.description
            itemView.setOnClickListener {
                listener.onNoteClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        return NoteHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.bind(noteList[position], listener)
    }

    override fun getItemCount() = noteList.size

    fun getItemByPosition(position: Int) = noteList[position]

    /**
     * Функция для обновления и инициализации списка заметок
     */
    fun updateAdapter(data: MutableList<Note>) {
        noteList.clear()
        noteList.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * Функция для удаления заметки из списка адаптера
     */
    fun removeItem(position: Int) {
        noteList.removeAt(position)
        notifyItemRangeChanged(0, itemCount)
        notifyItemRemoved(position)
    }
}
