package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.study.notesapp.databinding.FragmentCreateNoteBinding

/**
 * Фрагмент, на котором размещены поля для создания заметки
 */
class CreateNoteFragment : Fragment(), Contract.CreateNoteView {
    private lateinit var binding: FragmentCreateNoteBinding
    private var presenter: CreateNotePresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreateNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CreateNotePresenter(requireContext(), this)
        initViews()
    }

    private fun initViews() {
        with(binding.buttonCreateNote) {
            this.setOnClickListener {
                presenter?.saveNote(
                    null,
                    binding.title.text.toString(),
                    binding.description.text.toString()
                )
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }
}
