package com.example.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.NotesApplication
import com.example.todolist.R
import com.example.todolist.mvp.presenter.NoteListPresenter
import com.example.todolist.mvp.view.NoteListView
import com.example.todolist.persistence.Note
import com.example.todolist.recycler.NotesAdapter
import com.example.todolist.recycler.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class NoteListActivity: AppCompatActivity(), NoteListView, OnItemClickListener {

    @Inject
    lateinit var presenter: NoteListPresenter

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NotesApplication.appComponent.inject(this)

        presenter.handler = Handler(mainLooper)
        presenter.attachView(this)

        buildRecycler()

        findViewById<FloatingActionButton>(R.id.activity_note_list__add_note_fab)
                .setOnClickListener { startEditorForNewNote() }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadNoteList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        notesAdapter.items = notes
    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(this, NoteEditorActivity::class.java)
        intent.putExtra(NOTE_ID_START_EDITOR, notesAdapter.items[position].id)
        startActivity(intent)
    }

    private fun startEditorForNewNote() {
        val intent = Intent(this, NoteEditorActivity::class.java)
        startActivity(intent)
    }

    private fun buildRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.activity_note_list__notes_recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(emptyList(), this)
        recycler.adapter = notesAdapter
    }

}