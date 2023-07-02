package ru.study.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.launch
import ru.study.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        Log.v("MainActivity", "Main activity on create")
        initViews()
        StorageNotes.setDb(this)
        putDbDataToAdapter()
    }

    private fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = CustomRecyclerAdapter { note ->
            startActivity(Intent(this, DetailsNoteActivity::class.java).putExtra("item_hash", note.hashCode()))
        }
        bindingMain.recyclerView.adapter = adapter
    }

    private fun initButtons() {
        bindingMain.noteButton.let {
            it.setOnClickListener {
                startActivity(CreateNoteActivity.newIntent(this@MainActivity))
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

    override fun onResume() {
        super.onResume()

        Log.v("MainActivity", "Main activity onResume notes: ${getNoteList()}")
    }

    private fun getNoteList(): List<Note> {
        return StorageNotes.allNotes
    }
}