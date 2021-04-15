package com.example.todolist.screens.noteeditor

import android.content.Context
import com.example.todolist.R
import com.example.todolist.model.NoteModel
import com.example.todolist.persistence.Note
import com.example.todolist.screens.BaseVMObservable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteEditorViewModel
@Inject
constructor(
    private val noteModel: NoteModel,
    private val context: Context
) {

    val observableState = VMObservableState()

    fun loadNote() {
        if (observableState.id != null) {
            noteModel.loadNoteById(observableState.id!!) {
                observableState.id = it.id
                observableState.title = it.title
                observableState.text = it.text
            }
        }
    }

    fun saveNote() {
        if (validateTitleAndText()) {
            val note = Note(
                    observableState.id,
                    observableState.title,
                    observableState.text)

            if (note.id == null) {
                insertNote(note)
            } else {
                updateNote(note)
            }
        }
    }

    fun clear() {
        observableState.id = null
        observableState.title = ""
        observableState.text = ""
        observableState.needFinish = false
        observableState.toastText = null
    }

    private fun insertNote(note: Note) {
        noteModel.insertNote(note) {
            observableState.needFinish = true
        }
    }

    private fun updateNote(note: Note) {
        noteModel.updateNote(note) {
            observableState.needFinish = true
        }
    }

    private fun validateTitleAndText(): Boolean {
        if (observableState.title.isBlank()) {
            observableState.toastText = context.getString(R.string.editor_title_is_blank_msg)
            return false
        }
        if (observableState.text.isBlank()) {
            observableState.toastText = context.getString(R.string.editor_text_is_blank_msg)
            return false
        }
        return true
    }


    class VMObservableState : BaseVMObservable<VMStateObserver>() {
        var id: Long? = null

        var title: String = ""
            set(value) {
                field = value
                observers.forEach { it.onTitleLoaded(value) }
            }

        var text: String = ""
            set(value) {
                field = value
                observers.forEach { it.onTextLoaded(value) }
            }

        var needFinish = false
            set(value) {
                field = value
                if (value) observers.forEach { it.onNeedFinish() }
            }

        var toastText: String? = null
            set(value) {
                field = value
                if (value != null) observers.forEach { it.onShowToast(value) }
            }

    }

    interface VMStateObserver {

        fun onTitleLoaded(title: String)

        fun onTextLoaded(text: String)

        fun onNeedFinish()

        fun onShowToast(message: String)

    }

}