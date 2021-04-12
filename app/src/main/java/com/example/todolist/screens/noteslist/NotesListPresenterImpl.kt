package com.example.todolist.screens.noteslist

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

    init {
        notesListModel.addListener(this)
    }

    override fun loadNoteList() {
        notesListModel.loadAllNotes()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        notesListView?.onAllNotesLoaded(notes)
    }

    override fun attachView(notesListView: NotesListView) {
        this.notesListView = notesListView
    }

    override fun detachView() {
        notesListView = null
    }

}