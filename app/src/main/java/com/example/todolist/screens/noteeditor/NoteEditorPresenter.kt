package com.example.todolist.screens.noteeditor

import com.example.todolist.persistence.Note

interface NoteEditorPresenter {

    fun loadNoteById(id: Long)

    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun attachView(noteEditorView: NoteEditorView)

    fun detachView()

}