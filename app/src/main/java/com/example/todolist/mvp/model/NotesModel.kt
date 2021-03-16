package com.example.todolist.mvp.model

import com.example.todolist.mvp.presenter.NoteModelListener
import com.example.todolist.persistence.Note

interface NotesModel {

    fun loadAllNotes()

    fun loadNoteById(id: Long)

    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

    fun addNoteModelListener(listener: NoteModelListener)

    fun deleteNoteModelListener(listener: NoteModelListener)

}