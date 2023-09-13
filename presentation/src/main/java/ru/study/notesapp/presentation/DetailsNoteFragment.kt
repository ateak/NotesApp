package ru.study.notesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.study.notesapp.domain.models.Note
import ru.study.notesapp.presentation.databinding.FragmentDetailsNoteBinding

/**
 * Фрагмент для размещения подробного описания заметки
 */
class DetailsNoteFragment : Fragment() {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private val detailsNoteViewModel by viewModel<DetailsNoteViewModel>()
    private val args: DetailsNoteFragmentArgs by navArgs()

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

        detailsNoteViewModel.selectNote(args.NoteId)
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            detailsNoteViewModel.note.collect {
                bindingDetailsNote.title.setText(it.title)
                bindingDetailsNote.description.setText(it.description)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        detailsNoteViewModel.updateNote(
            Note(
                args.NoteId,
                bindingDetailsNote.title.text.toString(),
                bindingDetailsNote.description.text.toString()
            )
        )
    }
}
