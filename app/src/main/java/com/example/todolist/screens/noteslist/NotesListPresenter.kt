package com.example.todolist.screens.noteslist

interface NotesListPresenter {

    fun loadNoteList()
    
    fun attachView(notesListView: NotesListView)

    fun detachView()

}