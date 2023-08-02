package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.study.notesapp.databinding.FragmentDetailsNoteBinding

/**
 * Фрагмент для размещения подробного описания заметки
 */
class DetailsNoteFragment : Fragment() {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var noteId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingDetailsNote = FragmentDetailsNoteBinding.inflate(inflater)
        return bindingDetailsNote.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onPause() {
        super.onPause()
        Log.v("DetailsNoteFragment", "DetailsNote Fragment onPause")
        viewModel.updateNote(
            noteId,
            bindingDetailsNote.title.text.toString(),
            bindingDetailsNote.description.text.toString()
        )
    }

    private fun initViews() {
        viewModel.note.observe(this) {
            noteId = it.id!!
            bindingDetailsNote.title.setText(it.title)
            bindingDetailsNote.description.setText(it.description)
        }
    }
}
