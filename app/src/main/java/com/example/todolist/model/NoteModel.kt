package com.example.todolist.model

import android.os.Handler
import android.os.Looper
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteModel
@Inject
constructor(
        private val noteDao: NoteDao,
        private val executorService: ExecutorService
) {

    private val handler = Handler(Looper.getMainLooper())

    private val listeners: MutableList<NoteModelListener> = ArrayList()

    fun loadAllNotes() {
        executorService.execute {
            val notesList: List<Note> = noteDao.getAllOrderByIdDesc()
            handler.post {
                listeners.forEach { it.onAllNotesLoaded(notesList)}
            }
        }
    }

    fun loadNoteById(id: Long) {
        executorService.execute {
            val note = noteDao.getById(id)
            handler.post {
                listeners.forEach { l -> l.onNoteByIdLoaded(note)}
            }
        }
    }

    fun insertNote(note: Note) {
        executorService.execute {
            val id = noteDao.insert(note)
            handler.post {
                listeners.forEach { l -> l.onNoteInserted(id) }
            }
        }
    }

    fun updateNote(note: Note) {
        executorService.execute {
            noteDao.update(note)
            handler.post {
                listeners.forEach { l -> l.onNoteUpdated() }
            }
        }
    }

    fun addListener(listener: NoteModelListener) {
        listeners.add(listener)
    }

    fun deleteListener(listener: NoteModelListener) {
        listeners.remove(listener)
    }

}