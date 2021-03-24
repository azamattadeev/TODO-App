package com.example.todolist.screens.noteslist

import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.ExecutorService

class NotesListModelImpl(private val noteDao: NoteDao,
                         private val executorService: ExecutorService) : NotesListModel {

    private val listeners: MutableList<NotesListModelListener> = ArrayList()

    override fun loadAllNotes() {
        executorService.execute {
            val notesList: List<Note> = noteDao.getAllOrderByIdDesc()
            listeners.forEach { l -> l.onAllNotesLoaded(notesList)}
        }
    }

    override fun addListener(listener: NotesListModelListener) {
        listeners.add(listener)
    }

    override fun deleteListener(listener: NotesListModelListener) {
        listeners.remove(listener)
    }

}