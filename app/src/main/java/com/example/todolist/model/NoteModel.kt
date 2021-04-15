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

    fun loadAllNotes(callback: (List<Note>) -> Unit) {
        executorService.execute {
            val notesList: List<Note> = noteDao.getAllOrderByIdDesc()
            handler.post {
                callback(notesList)
            }
        }
    }

    fun loadNoteById(id: Long, callback: (Note) -> Unit) {
        executorService.execute {
            val note = noteDao.getById(id)
            handler.post {
                callback(note)
            }
        }
    }

    fun insertNote(note: Note, callback: () -> Unit) {
        executorService.execute {
            val id = noteDao.insert(note)
            val newNote = Note(id, note.title, note.text)
            handler.post {
                listeners.forEach { it.onNoteInserted(newNote) }
                callback()
            }
        }
    }

    fun updateNote(note: Note, callback: () -> Unit) {
        executorService.execute {
            noteDao.update(note)
            handler.post {
                listeners.forEach { it.onNoteUpdated(note) }
                callback()
            }
        }
    }

    fun addListener(listener: NoteModelListener) {
        listeners.add(listener)
    }

}