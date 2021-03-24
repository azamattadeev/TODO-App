package com.example.todolist.screens.noteeditor

import com.example.todolist.persistence.Note

interface NoteEditorModel {

    fun loadNoteById(id: Long)

    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun addListener(listener: NoteEditorModelListener)

    fun deleteListener(listener: NoteEditorModelListener)

}