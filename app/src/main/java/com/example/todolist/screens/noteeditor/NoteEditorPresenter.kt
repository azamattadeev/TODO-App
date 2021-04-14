package com.example.todolist.screens.noteeditor

import com.example.todolist.model.NoteModel
import com.example.todolist.model.NoteModelListener
import com.example.todolist.persistence.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteEditorPresenter
@Inject
constructor(
    private val noteModel: NoteModel
) : NoteModelListener {

    private var noteEditorView: NoteEditorView? = null

    init {
        noteModel.addListener(this)
    }

    fun loadNoteById(id: Long) {
        noteModel.loadNoteById(id)
    }

    fun insertNote(note: Note) {
        noteModel.insertNote(note)
    }

    fun updateNote(note: Note) {
        noteModel.updateNote(note)
    }

    fun attachView(noteEditorView: NoteEditorView) {
        this.noteEditorView = noteEditorView
    }

    fun detachView() {
        this.noteEditorView = null
    }

    override fun onNoteByIdLoaded(note: Note) {
        noteEditorView?.onNoteByIdLoaded(note)
    }

    override fun onNoteInserted(id: Long) {
        savingFinished()
    }

    override fun onNoteUpdated() {
        savingFinished()
    }

    override fun onAllNotesLoaded(notes: List<Note>) {

    }

    private fun savingFinished() {
        noteEditorView?.onSavingFinished()
    }

}