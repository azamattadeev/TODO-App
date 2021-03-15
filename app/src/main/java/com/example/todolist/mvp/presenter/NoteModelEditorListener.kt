package com.example.todolist.mvp.presenter

import com.example.todolist.persistence.Note

interface NoteModelEditorListener {

    fun onNoteByIdLoaded(note: Note)

    fun onNoteInserted(note: Note)

    fun onNoteUpdated(note: Note)

    fun onNoteDeleted(note: Note)

}