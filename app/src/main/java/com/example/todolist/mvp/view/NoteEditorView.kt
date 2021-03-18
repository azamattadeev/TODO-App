package com.example.todolist.mvp.view

import com.example.todolist.persistence.Note

interface NoteEditorView {

    fun onNoteByIdLoaded(note: Note)

    fun onSavingFinished()

}