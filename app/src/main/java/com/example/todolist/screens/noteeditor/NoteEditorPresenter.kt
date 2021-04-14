package com.example.todolist.screens.noteeditor

import com.example.todolist.model.NoteModel
import com.example.todolist.persistence.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteEditorPresenter
@Inject
constructor(
    private val noteModel: NoteModel
) {

    private var noteEditorView: NoteEditorView? = null

    fun loadNoteById(id: Long) {
        noteModel.loadNoteById(id) {
            this.noteEditorView?.onNoteByIdLoaded(it)
        }
    }

    fun insertNote(note: Note) {
        noteModel.insertNote(note, ::onSavingFinished)
    }

    fun updateNote(note: Note) {
        noteModel.updateNote(note, ::onSavingFinished)
    }

    fun attachView(noteEditorView: NoteEditorView) {
        this.noteEditorView = noteEditorView
    }

    fun detachView() {
        this.noteEditorView = null
    }

    private fun onSavingFinished() {
        noteEditorView?.onSavingFinished()
    }

}