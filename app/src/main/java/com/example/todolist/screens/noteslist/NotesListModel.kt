package com.example.todolist.screens.noteslist

interface NotesListModel {

    fun loadAllNotes()

    fun addListener(listener: NotesListModelListener)

    fun deleteListener(listener: NotesListModelListener)

}