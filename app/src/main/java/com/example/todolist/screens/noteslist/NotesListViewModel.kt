package com.example.todolist.screens.noteslist

import com.example.todolist.model.NoteModel
import com.example.todolist.model.NoteModelListener
import com.example.todolist.persistence.Note
import com.example.todolist.screens.BaseVMObservable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesListViewModel
@Inject
constructor(
    private val noteModel: NoteModel
) : NoteModelListener {

    val observableState = VMObservableState()

    init {
        noteModel.addListener(this)
    }

    fun loadNotesList() {
        noteModel.loadAllNotes { observableState.notes = it.toMutableList() }
    }

    override fun onNoteInserted(note: Note) {
        observableState.notes.add(0, note)
        observableState.insertNote = note
    }

    override fun onNoteUpdated(note: Note) {
        val index = observableState.notes.indexOf(observableState.notes.find { it.id == note.id })
        observableState.notes[index] = note
        observableState.updateNote = note
    }


    class VMObservableState : BaseVMObservable<VMStateObserver>() {
        var notes: MutableList<Note> = mutableListOf()
            set(value) {
                field = value
                observers.forEach { it.onNotesListChanged(value) }
            }

        var insertNote: Note? = null
            set(value) {
                field = value
                if (value != null) observers.forEach { it.onNoteInserted(value) }
            }

        var updateNote: Note? = null
            set(value) {
                field = value
                if (value != null) observers.forEach { it.onNoteUpdated(value) }
                }
    }

    interface VMStateObserver {

        fun onNotesListChanged(notes: List<Note>)

        fun onNoteInserted(note: Note)

        fun onNoteUpdated(note: Note)

    }

}

