package com.example.todolist.screens.noteslist

import com.example.todolist.model.NoteModel
import com.example.todolist.model.NoteModelListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesListPresenter
@Inject
constructor(
    private val noteModel: NoteModel
) : NoteModelListener {

    private var notesListView: NotesListView? = null

    init {
        noteModel.addListener(this)
    }

    fun loadNotesList() {
        noteModel.loadAllNotes {
            notesListView?.onAllNotesLoaded(it)
        }
    }

    fun attachView(notesListView: NotesListView) {
        this.notesListView = notesListView
    }

    fun detachView() {
        notesListView = null
    }

    override fun onDataChanged() {
        notesListView?.loadNotes()
    }

}