package com.example.todolist.screens.noteeditor

import android.os.Handler
import com.example.todolist.persistence.Note

class NoteEditorPresenter(private val noteEditorModel: NoteEditorModel) : NoteEditorModelListener {
    private var noteEditorView: NoteEditorView? = null
    lateinit var handler: Handler

    init {
        noteEditorModel.addListener(this)
    }

    fun loadNoteById(id: Long) {
        noteEditorModel.loadNoteById(id)
    }

    fun insertNote(note: Note) {
        noteEditorModel.insertNote(note)
    }

    fun updateNote(note: Note) {
        noteEditorModel.updateNote(note)
    }

    override fun onNoteByIdLoaded(note: Note) {
        handler.post { noteEditorView?.onNoteByIdLoaded(note) }
    }

    override fun onNoteInserted(id: Long) {
        savingFinished()
    }

    override fun onNoteUpdated() {
        savingFinished()
    }

    private fun savingFinished() {
        handler.post { noteEditorView?.onSavingFinished() }
    }

    fun attachView(noteEditorView: NoteEditorView) {
        this.noteEditorView = noteEditorView
    }

    fun detachView() {
        this.noteEditorView = null
    }

}