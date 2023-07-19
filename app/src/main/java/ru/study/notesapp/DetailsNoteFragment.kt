package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import ru.study.notesapp.databinding.FragmentDetailsNoteBinding

class DetailsNoteFragment : Fragment() {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private var note: Note? = null
    private val dataModel: DataModel by activityViewModels()
    private var hash: Int = 0

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
        initViews()
    }

    override fun onPause() {
        super.onPause()
        Log.v("DetailsNoteFragment", "DetailsNote Fragment onPause")
        note?.title = bindingDetailsNote.title.text.toString()
        note?.description = bindingDetailsNote.description.text.toString()
        note?.let { StorageNotes.updateNote(it) }
    }

    private fun initViews() {
        dataModel.hash.observe(activity as LifecycleOwner) { hash = it }
        note = StorageNotes.allNotes.find { it.hashCode() == hash }
        bindingDetailsNote.title.setText(note?.title)
        bindingDetailsNote.description.setText(note?.description)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsNoteFragment()
    }
}