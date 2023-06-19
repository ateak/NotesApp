package ru.study.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MainActivity", "Main activity on create")

        initViews()
    }

    private fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        adapter = CustomRecyclerAdapter { note ->
            startActivity(Intent(this, DetailsNoteActivity::class.java).putExtra("item_hash", note.hashCode()))
        }
        recyclerView.adapter = adapter
    }

    private fun initButtons() {
        findViewById<Button>(R.id.note_button).let {
            it.setOnClickListener {
                startActivity(CreateNoteActivity.newIntent(this@MainActivity))
            }
        }
    }

    override fun onResume() {

        super.onResume()
        Log.v("MainActivity","Main activity onResume notes: ${getNoteList()}")

        adapter.addData(getNoteList())
    }

    private fun getNoteList(): MutableList<Note> {
        return StorageNotes.allNotes
    }

}