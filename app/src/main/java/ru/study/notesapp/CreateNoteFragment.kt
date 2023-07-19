package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.study.notesapp.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
   // private val storageNotes = StorageNotes(requireContext())

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
        initViews()
    }

    //TODO создать объект storageNotes
    private fun initViews() {
        with(binding.buttonCreateNote) {
            this.setOnClickListener {
                StorageNotes(requireContext()).addNote(
                    Note(
                        null,
                        binding.title.text.toString(),
                        binding.description.text.toString()
                    )
                )
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView2, MainFragment.newInstance())
                    .commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateNoteFragment()
    }
}
