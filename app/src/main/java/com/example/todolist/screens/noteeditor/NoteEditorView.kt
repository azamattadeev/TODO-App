package com.example.todolist.screens.noteeditor

import com.example.todolist.persistence.Note

interface NoteEditorView {

    fun onNoteByIdLoaded(note: Note)

    fun onSavingFinished()

}