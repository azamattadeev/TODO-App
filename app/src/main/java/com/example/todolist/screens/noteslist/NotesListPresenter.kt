package com.example.todolist.screens.noteslist

import com.example.todolist.model.NoteModel
import com.example.todolist.model.NoteModelListener
import com.example.todolist.persistence.Note
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

    fun loadNoteList() {
        noteModel.loadAllNotes()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {
        notesListView?.onAllNotesLoaded(notes)
    }

    override fun onNoteInserted(id: Long) {
        notesListView?.loadNotes()
    }

    override fun onNoteUpdated() {
        notesListView?.loadNotes()
    }

    override fun onNoteByIdLoaded(note: Note) {

    }

    fun attachView(notesListView: NotesListView) {
        this.notesListView = notesListView
    }

    fun detachView() {
        notesListView = null
    }

}