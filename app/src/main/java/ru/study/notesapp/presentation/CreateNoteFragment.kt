package ru.study.notesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.study.notesapp.databinding.FragmentCreateNoteBinding
import ru.study.notesapp.domain.models.Note

/**
 * Фрагмент, на котором размещены поля для создания заметки
 */
class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private val createNoteViewModel by viewModel<CreateNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentCreateNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding.buttonCreateNote) {
            this.setOnClickListener {
                createNoteViewModel.saveNote(
                    Note(
                        0,
                        binding.title.text.toString(),
                        binding.description.text.toString()
                    )
                )
                findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToMainFragment())
            }
        }
    }
}
