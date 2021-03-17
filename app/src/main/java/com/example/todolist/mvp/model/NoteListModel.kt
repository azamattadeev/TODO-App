package com.example.todolist.mvp.model

import com.example.todolist.mvp.model.listeners.NoteListModelListener

interface NoteListModel {

    fun loadAllNotes()

    fun addListener(listener: NoteListModelListener)

    fun deleteListener(listener: NoteListModelListener)

}