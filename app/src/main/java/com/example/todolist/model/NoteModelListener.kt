package com.example.todolist.model

import com.example.todolist.persistence.Note

interface NoteModelListener {

    fun onNoteInserted(note: Note)

    fun onNoteUpdated(note: Note)

}