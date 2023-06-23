package ru.study.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    override fun onResume() {
        super.onResume()

        Log.v("MainActivity", "Main activity onResume notes: ${getNoteList()}")
        adapter.addData(getNoteList())
    }

    private fun getNoteList(): MutableList<Note> {
        return StorageNotes.allNotes
    }
}