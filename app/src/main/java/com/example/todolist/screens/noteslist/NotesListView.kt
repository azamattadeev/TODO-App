package com.example.todolist.screens.noteslist

import com.example.todolist.persistence.Note

interface NotesListView {

    fun onAllNotesLoaded(notes: List<Note>)

}