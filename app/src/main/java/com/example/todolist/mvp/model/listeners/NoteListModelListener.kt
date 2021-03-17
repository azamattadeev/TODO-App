package com.example.todolist.mvp.model.listeners

import com.example.todolist.persistence.Note

interface NoteListModelListener {

    fun onAllNotesLoaded(notes: List<Note>)

}