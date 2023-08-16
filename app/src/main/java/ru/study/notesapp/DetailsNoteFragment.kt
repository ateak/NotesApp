package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.study.notesapp.databinding.FragmentDetailsNoteBinding

/**
 * Фрагмент для размещения подробного описания заметки
 */
class DetailsNoteFragment : Fragment() {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private lateinit var detailsNoteViewModel: DetailsNoteViewModel
    private val args: DetailsNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingDetailsNote = FragmentDetailsNoteBinding.inflate(inflater)
        detailsNoteViewModel =
            ViewModelProvider(this, ViewModelFactory(requireContext())).get(DetailsNoteViewModel::class.java)

        return bindingDetailsNote.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsNoteViewModel.handle(SelectNoteEvent(args.NoteId))
    }

    override fun onResume() {
        super.onResume()

        detailsNoteViewModel.stateNote.onEach {
            bindingDetailsNote.title.setText(it.note.title)
            bindingDetailsNote.description.setText(it.note.description)
        }.launchIn(lifecycleScope)
    }

    override fun onPause() {
        super.onPause()

        detailsNoteViewModel.handle(
            UpdateNoteEvent(
                Note(
                    args.NoteId,
                    bindingDetailsNote.title.text.toString(),
                    bindingDetailsNote.description.text.toString()
                )
            )
        )
    }
}
