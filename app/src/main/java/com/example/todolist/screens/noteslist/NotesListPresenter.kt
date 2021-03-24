package com.example.todolist.screens.noteslist

import android.os.Handler
import com.example.todolist.persistence.Note


class NotesListPresenter(private val notesListModel: NotesListModel) : NotesListModelListener {
    private var notesListView: NotesListView? = null
    lateinit var handler: Handler

    init {
        notesListModel.addListener(this)
    }

    fun loadNoteList() {
        notesListModel.loadAllNotes()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        handler.post { notesListView?.onAllNotesLoaded(notes) }
    }

    fun attachView(notesListView: NotesListView) {
        this.notesListView = notesListView
    }

    fun detachView() {
        notesListView = null
    }

}