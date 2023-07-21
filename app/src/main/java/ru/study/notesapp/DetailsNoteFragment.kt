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

class DetailsNoteFragment : Fragment(), Contract.DetailsNoteView {
    private lateinit var bindingDetailsNote: FragmentDetailsNoteBinding
    private var presenter: DetailsNotePresenter? = null

    private var note: Note? = null
    //private val storageNotes = context?.let { StorageNotes(it) }
    private val dataModel: DataModel by activityViewModels()
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
        initViews()
        presenter = DetailsNotePresenter(requireContext(),this)

    }

    //TODO создать объект storageNotes
    override fun onPause() {
        super.onPause()
        Log.v("DetailsNoteFragment", "DetailsNote Fragment onPause")
        note?.title = bindingDetailsNote.title.text.toString()
        note?.description = bindingDetailsNote.description.text.toString()
        note?.let { StorageNotes(requireContext())?.updateNote(it) }
    }

    //TODO создать объект storageNotes
    private fun initViews() {
        dataModel.noteId.observe(activity as LifecycleOwner) { noteId = it }
        note = StorageNotes(requireContext())?.allNotes?.find { it.id == noteId }
        bindingDetailsNote.title.setText(note?.title)
        bindingDetailsNote.description.setText(note?.description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsNoteFragment()
    }
}
