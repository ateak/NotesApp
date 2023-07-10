package ru.study.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ru.study.notesapp.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {
   private lateinit var binding: FragmentCreateNoteBinding
   //private val dataModel: DataModel by activityViewModels()

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

    private fun initViews() {
        with(binding.buttonCreateNote) {
            this.setOnClickListener {
                //addNote перенести во ViewModel (saveNote(title, description))
                StorageNotes.addNote(
                    Note(
                        null,
                        binding.title.text.toString(),
                        binding.description.text.toString()
                    )
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateNoteFragment()
    }
}