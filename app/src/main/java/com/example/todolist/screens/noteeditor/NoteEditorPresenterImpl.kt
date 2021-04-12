package com.example.todolist.screens.noteeditor

import com.example.todolist.persistence.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteEditorPresenterImpl
@Inject
constructor(
    private val noteEditorModel: NoteEditorModel
) : NoteEditorPresenter, NoteEditorModelListener {

    private var noteEditorView: NoteEditorView? = null

    init {
        noteEditorModel.addListener(this)
    }

    override fun loadNoteById(id: Long) {
        noteEditorModel.loadNoteById(id)
    }

    override fun insertNote(note: Note) {
        noteEditorModel.insertNote(note)
    }

    override fun updateNote(note: Note) {
        noteEditorModel.updateNote(note)
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

    private fun savingFinished() {
        noteEditorView?.onSavingFinished()
    }

    override fun attachView(noteEditorView: NoteEditorView) {
        this.noteEditorView = noteEditorView
    }

    override fun detachView() {
        this.noteEditorView = null
    }

}