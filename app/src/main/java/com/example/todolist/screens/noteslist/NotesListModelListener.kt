package com.example.todolist.screens.noteslist

import com.example.todolist.persistence.Note

interface NotesListModelListener {

    fun onAllNotesLoaded(notes: List<Note>)

}