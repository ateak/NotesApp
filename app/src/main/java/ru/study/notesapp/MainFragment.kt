package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.study.notesapp.databinding.FragmentMainBinding

/**
 * Фрагмент для размещения списка заметок
 */
class MainFragment : Fragment(), Contract.MainView, Listener {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: NoteAdapter
    private var presenter: MainPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingMain = FragmentMainBinding.inflate(inflater)
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter = MainPresenter(requireContext(), this)
    }

    private fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = NoteAdapter(this)

        val swapHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter?.deleteNote(adapter.getItemByPosition(viewHolder.position).id)
                adapter.removeItem(viewHolder.position)
            }
        })
        swapHelper.attachToRecyclerView(bindingMain.recyclerView)
        bindingMain.recyclerView.adapter = adapter
    }

    private fun initButtons() {
        bindingMain.noteButton.setOnClickListener {
            findNavController().navigate(R.id.createNoteFragment)
        }
    }

    override fun showNotes(noteList: MutableList<Note>) {
        adapter.updateAdapter(noteList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }

    override fun onNoteClick(note: Note) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsNoteFragment(note.id!!)
        findNavController().navigate(action)
    }
}
