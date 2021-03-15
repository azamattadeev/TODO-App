package com.example.todolist.mvp.model

import com.example.todolist.persistence.Note

interface NotesModel {

    fun loadAllNotes()

    fun loadNoteById(id: Int)

    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

}