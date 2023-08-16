package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.study.notesapp.databinding.FragmentCreateNoteBinding

/**
 * Фрагмент, на котором размещены поля для создания заметки
 */
class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var createNoteViewModel: CreateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreateNoteBinding.inflate(inflater)
        createNoteViewModel =
            ViewModelProvider(this, ViewModelFactory(requireContext())).get(CreateNoteViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding.buttonCreateNote) {
            this.setOnClickListener {
                createNoteViewModel.handle(
                    SaveNoteEvent(
                        Note(
                            0,
                            binding.title.text.toString(),
                            binding.description.text.toString()
                        )
                    )
                )
                findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToMainFragment())
            }
        }
    }
}
