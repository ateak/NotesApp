package ru.study.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.launch
import ru.study.notesapp.databinding.ActivityMainBinding
import ru.study.notesapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: CustomRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingMain = FragmentMainBinding.inflate(inflater)
        Log.v("MainFragment", "Main fragment on create")
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        context?.let { StorageNotes.setDb(it) }
        putDbDataToAdapter()
    }

    private fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = CustomRecyclerAdapter { note ->
            // переписать на фрагмент и это надо запихнуть во viewModel.createNote()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView2, DetailsNoteFragment.newInstance())
                .commit()
            //startActivity(Intent(this, DetailsNoteActivity::class.java).putExtra("item_hash", note.hashCode()))
        }
        bindingMain.recyclerView.adapter = adapter
    }

    private fun initButtons() {
        bindingMain.noteButton.let {
            it.setOnClickListener {
                // переписать на фрагмент
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView2, CreateNoteFragment.newInstance())
                    .commit()
                //startActivity(CreateNoteActivity.newIntent(this@MainActivity))
            }
        }
    }

    /**
     * Функция для обновления локального списка заметок данными из БД и передачи их в адаптер
     */
    private fun putDbDataToAdapter() {
        lifecycle.coroutineScope.launch {
            StorageNotes.loadNotesFromDb().collect() {
                StorageNotes.allNotes.clear()
                StorageNotes.allNotes.addAll(it)
                adapter.updateAdapter(it)
            }
        }
    }

    private fun getNoteList(): List<Note> {
        return StorageNotes.allNotes
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}