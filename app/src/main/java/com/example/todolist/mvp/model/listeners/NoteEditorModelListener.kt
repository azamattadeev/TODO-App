package com.example.todolist.mvp.model.listeners

import com.example.todolist.persistence.Note

interface NoteEditorModelListener {

    fun onNoteByIdLoaded(note: Note)

    fun onNoteInserted(id: Long)

    fun onNoteUpdated()

}