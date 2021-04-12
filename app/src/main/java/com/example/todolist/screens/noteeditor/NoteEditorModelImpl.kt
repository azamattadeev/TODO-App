package com.example.todolist.screens.noteeditor

import android.os.Handler
import android.os.Looper
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteEditorModelImpl
@Inject
constructor(
    private val noteDao: NoteDao,
    private val executorService: ExecutorService
) : NoteEditorModel {

    private val handler = Handler(Looper.getMainLooper())

    private val listeners: MutableList<NoteEditorModelListener> = ArrayList()

    override fun loadNoteById(id: Long) {
        executorService.execute {
            val note = noteDao.getById(id)
            handler.post {
                listeners.forEach { l -> l.onNoteByIdLoaded(note)}
            }
        }
    }

    override fun insertNote(note: Note) {
        executorService.execute {
            val id = noteDao.insert(note)
            handler.post {
                listeners.forEach { l -> l.onNoteInserted(id) }
            }
        }
    }

    override fun updateNote(note: Note) {
        executorService.execute {
            noteDao.update(note)
            handler.post {
                listeners.forEach { l -> l.onNoteUpdated() }
            }
        }
    }

    override fun addListener(listener: NoteEditorModelListener) {
        listeners.add(listener)
    }

    override fun deleteListener(listener: NoteEditorModelListener) {
        listeners.remove(listener)
    }


}