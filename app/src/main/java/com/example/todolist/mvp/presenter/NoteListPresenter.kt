package com.example.todolist.mvp.presenter

import android.os.Handler
import com.example.todolist.NotesApplication
import com.example.todolist.mvp.model.NoteListModel
import com.example.todolist.mvp.model.listeners.NoteListModelListener
import com.example.todolist.mvp.view.NoteListView
import com.example.todolist.persistence.Note


class NoteListPresenter(val noteListModel: NoteListModel) : NoteListModelListener {
    private var noteListView: NoteListView? = null
    lateinit var handler: Handler

    init {
        NotesApplication.appComponent.inject(this)
        noteListModel.addListener(this)
    }

    fun loadNoteList() {
        noteListModel.loadAllNotes()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        handler.post { noteListView?.onAllNotesLoaded(notes) }
    }

    fun attachView(noteListView: NoteListView) {
        this.noteListView = noteListView
    }

    fun detachView() {
        noteListView = null
    }

}