package com.example.todolist.screens.noteslist

import android.os.Handler
import android.os.Looper
import com.example.todolist.persistence.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesListPresenterImpl
@Inject
constructor(
    private val notesListModel: NotesListModel
) : NotesListPresenter, NotesListModelListener {

    private var notesListView: NotesListView? = null
    private val handler = Handler(Looper.getMainLooper())

    init {
        notesListModel.addListener(this)
    }

    override fun loadNoteList() {
        notesListModel.loadAllNotes()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        handler.post { notesListView?.onAllNotesLoaded(notes) }
    }

    override fun attachView(notesListView: NotesListView) {
        this.notesListView = notesListView
    }

    override fun detachView() {
        notesListView = null
    }

}