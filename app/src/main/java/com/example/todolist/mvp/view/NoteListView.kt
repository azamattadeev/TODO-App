package com.example.todolist.mvp.view

import com.example.todolist.persistence.Note

interface NoteListView {

    fun onAllNotesLoaded(notes: List<Note>)

    fun navigateToNoteEditor()

}