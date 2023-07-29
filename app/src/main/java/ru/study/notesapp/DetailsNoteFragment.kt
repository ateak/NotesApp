package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.study.notesapp.databinding.FragmentDetailsNoteBinding

/**
 * Фрагмент для размещения подробного описания заметки
 */
class DetailsNoteFragment : Fragment(), Contract.DetailsNoteView {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private var presenter: DetailsNotePresenter? = null
    private var noteId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingDetailsNote = FragmentDetailsNoteBinding.inflate(inflater)
        return bindingDetailsNote.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DetailsNotePresenter(requireContext(), this)
        initViews()
    }

    override fun onPause() {
        super.onPause()
        Log.v("DetailsNoteFragment", "DetailsNote Fragment onPause")
        presenter?.updateNote(
            noteId,
            bindingDetailsNote.title.text.toString(),
            bindingDetailsNote.description.text.toString()
        )
    }

    private fun initViews() {
        val args: DetailsNoteFragmentArgs by navArgs()
        noteId = args.NoteId
        presenter?.getNote(noteId)
    }

    override fun showNote(note: Note) {
        bindingDetailsNote.title.setText(note.title)
        bindingDetailsNote.description.setText(note.description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }
}
