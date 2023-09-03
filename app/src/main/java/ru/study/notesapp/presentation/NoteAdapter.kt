package ru.study.notesapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.study.notesapp.R
import ru.study.notesapp.databinding.RecyclerviewItemBinding
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.presentation.listeners.Listener

/**
 * Адаптер для отрисовки элемента заметки в списке
 */
class NoteAdapter(val listener: Listener) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var oldNoteList = listOf<Note>()

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingAdapter = RecyclerviewItemBinding.bind(itemView)
        fun bind(note: Note?, listener: Listener) = with(bindingAdapter) {
            textViewLarge.text = note?.title
            textViewSmall.text = note?.description
            itemView.setOnClickListener {
                if (note != null) {
                    listener.onNoteClick(note)
                }
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
        holder.bind(oldNoteList[position], listener)
    }

    override fun getItemCount() = oldNoteList.size

    fun getItemByPosition(position: Int) = oldNoteList[position]

    /**
     * Функция для обновления списка заметок на MainFragment
     */
    fun updateData(newNoteList: List<Note>) {
        val diffUtil = NoteDiffUtil(oldNoteList, newNoteList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldNoteList = newNoteList
        diffResult.dispatchUpdatesTo(this)
    }
}
