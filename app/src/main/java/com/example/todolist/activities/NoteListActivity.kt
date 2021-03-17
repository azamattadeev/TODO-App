package com.example.todolist.activities

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
import javax.inject.Inject

class NoteListActivity: AppCompatActivity(), NoteListView {

    @Inject lateinit var presenter: NoteListPresenter

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        NotesApplication.appComponent.inject(this)

        presenter.handler = Handler(Looper.getMainLooper())
        presenter.attachView(this)

        buildRecycler()

        presenter.loadNoteList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    fun navigateToNoteEditor() {
        TODO()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        notesAdapter.items = notes
    }

    private fun buildRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.activity_notes_list__notes_recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(emptyList())
        recycler.adapter = notesAdapter
    }

}