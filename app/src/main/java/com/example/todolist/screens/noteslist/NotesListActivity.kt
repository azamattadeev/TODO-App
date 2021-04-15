package com.example.todolist.screens.noteslist

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.NOTE_ID_START_EDITOR
import com.example.todolist.NotesApplication
import com.example.todolist.R
import com.example.todolist.persistence.Note
import com.example.todolist.screens.noteeditor.NoteEditorActivity
import com.example.todolist.screens.noteslist.recycler.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NotesListActivity: AppCompatActivity(), NotesListViewModel.VMStateObserver {

    @Inject
    lateinit var viewModel: NotesListViewModel

    private lateinit var recycler: RecyclerView
    private lateinit var rootFrameLayout: FrameLayout

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        recycler = findViewById(R.id.activity_note_list__notes_recycler)
        rootFrameLayout = findViewById(R.id.activity_note_list__frame_layout)

        NotesApplication.appComponent.inject(this)

        viewModel.observableState.subscribe(this)

        buildRecycler()

        findViewById<FloatingActionButton>(R.id.activity_note_list__add_note_fab)
                .setOnClickListener { startEditorForNewNote() }

        if (savedInstanceState == null) {
            viewModel.loadNotesList()
        } else {
            processViewModelState()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.observableState.unsubscribe(this)
    }

    override fun onNotesListChanged(notes: List<Note>) {
        notesAdapter.items = notes.toMutableList()
    }

    override fun onNoteInserted(note: Note) {
        notesAdapter.insertNote(note)
        recycler.layoutManager?.scrollToPosition(0)
        viewModel.observableState.insertNote = null
        showSnackBar(getString(R.string.notes_list_note_created))
    }

    override fun onNoteUpdated(note: Note) {
        notesAdapter.updateNote(note)
        viewModel.observableState.updateNote = null
        showSnackBar(getString(R.string.notes_list_note_updated))
    }

    private fun onNoteClicked(note: Note) {
        val intent = Intent(this, NoteEditorActivity::class.java)
        intent.putExtra(NOTE_ID_START_EDITOR, note.id)
        startActivity(intent)
    }

    private fun startEditorForNewNote() {
        val intent = Intent(this, NoteEditorActivity::class.java)
        startActivity(intent)
    }

    private fun processViewModelState() {
        notesAdapter.items = viewModel.observableState.notes
        val insertNote = viewModel.observableState.insertNote
        val updateNote = viewModel.observableState.updateNote
        if (insertNote != null) onNoteInserted(insertNote)
        if (updateNote != null) onNoteUpdated(updateNote)
    }

    private fun buildRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter { onNoteClicked(it) }
        recycler.adapter = notesAdapter
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(
                rootFrameLayout,
                msg,
                Snackbar.LENGTH_SHORT
        ).show()
    }

}