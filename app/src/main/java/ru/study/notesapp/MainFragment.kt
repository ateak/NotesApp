package ru.study.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.study.notesapp.databinding.FragmentMainBinding

/**
 * Фрагмент для размещения списка заметок
 */
class MainFragment : Fragment(), Listener {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingMain = FragmentMainBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this, ViewModelFactory(requireContext())).get(MainViewModel::class.java)
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.stateNoteList.noteList.onEach {
            adapter.updateData(it)
        }.launchIn(lifecycleScope)
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
                adapter.getItemByPosition(viewHolder.position).id?.let { mainViewModel.handle(DeleteNoteEvent(it)) }
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

    override fun onNoteClick(note: Note) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsNoteFragment(note.id)
        findNavController().navigate(action)
    }
}
