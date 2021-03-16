package com.example.todolist.mvp.model

import com.example.todolist.mvp.presenter.NoteModelListener
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.Executors

class NotesModelImpl(var noteDao: NoteDao) : NotesModel {
    private val listeners: MutableList<NoteModelListener> = ArrayList()
    private val executorsService = Executors.newSingleThreadExecutor()

    override fun loadAllNotes() {
        executorsService.execute {
            val notesList: List<Note> = noteDao.getAll()
            listeners.forEach { l -> l.onAllNotesLoaded(notesList)}
        }
    }

    override fun loadNoteById(id: Long) {
        executorsService.execute {
            val note = noteDao.getById(id)
            listeners.forEach { l -> l.onNoteByIdLoaded(note)}
        }
    }

    override fun insertNote(note: Note) {
        executorsService.execute {
            val id = noteDao.insert(note)
            listeners.forEach { l -> l.onNoteInserted(id) }
        }
    }

    override fun updateNote(note: Note) {
        executorsService.execute {
            noteDao.update(note)
            listeners.forEach { l -> l.onNoteUpdated() }
        }
    }

    override fun deleteNote(note: Note) {
        executorsService.execute {
            noteDao.delete(note)
            listeners.forEach { l -> l.onNoteDeleted() }
        }
    }

    override fun addNoteModelListener(listener: NoteModelListener) {
        listeners.add(listener)
    }

    override fun deleteNoteModelListener(listener: NoteModelListener) {
        listeners.remove(listener)
    }

}