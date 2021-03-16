package com.example.todolist.mvp.presenter

import com.example.todolist.persistence.Note

interface NoteModelListener {

    fun onAllNotesLoaded(notes: List<Note>)

    fun onNoteByIdLoaded(note: Note)

    fun onNoteInserted(id: Long)

    fun onNoteUpdated()

    fun onNoteDeleted()

}