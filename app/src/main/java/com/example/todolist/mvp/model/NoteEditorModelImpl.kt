package com.example.todolist.mvp.model

import com.example.todolist.mvp.model.listeners.NoteEditorModelListener
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.ExecutorService

class NoteEditorModelImpl(var noteDao: NoteDao, var executorService: ExecutorService) : NoteEditorModel {
    private val listeners: MutableList<NoteEditorModelListener> = ArrayList()

    override fun loadNoteById(id: Long) {
        executorService.execute {
            val note = noteDao.getById(id)
            listeners.forEach { l -> l.onNoteByIdLoaded(note)}
        }
    }

    override fun insertNote(note: Note) {
        executorService.execute {
            val id = noteDao.insert(note)
            listeners.forEach { l -> l.onNoteInserted(id) }
        }
    }

    override fun updateNote(note: Note) {
        executorService.execute {
            noteDao.update(note)
            listeners.forEach { l -> l.onNoteUpdated() }
        }
    }

    override fun addListener(listener: NoteEditorModelListener) {
        listeners.add(listener)
    }

    override fun deleteListener(listener: NoteEditorModelListener) {
        listeners.remove(listener)
    }


}