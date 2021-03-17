package com.example.todolist.mvp.model

import com.example.todolist.NotesApplication
import com.example.todolist.mvp.model.listeners.NoteListModelListener
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.ExecutorService

class NoteListModelImpl(val noteDao: NoteDao,
                        val executorService: ExecutorService) : NoteListModel {

    private val listeners: MutableList<NoteListModelListener> = ArrayList()

    override fun loadAllNotes() {
        executorService.execute {
            val notesList: List<Note> = noteDao.getAll()
            listeners.forEach { l -> l.onAllNotesLoaded(notesList)}
        }
    }

    override fun addListener(listener: NoteListModelListener) {
        listeners.add(listener)
    }

    override fun deleteListener(listener: NoteListModelListener) {
        listeners.remove(listener)
    }

}