package com.example.todolist.activities

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.NotesApplication
import com.example.todolist.R
import com.example.todolist.mvp.presenter.NoteEditorPresenter
import com.example.todolist.mvp.view.NoteEditorView
import com.example.todolist.persistence.Note
import javax.inject.Inject

class NoteEditorActivity: AppCompatActivity(), NoteEditorView {

    @Inject
    lateinit var presenter: NoteEditorPresenter

    private lateinit var title: EditText
    private lateinit var text: EditText
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        NotesApplication.appComponent.inject(this)

        presenter.handler = Handler(mainLooper)
        presenter.attachView(this)

        title = findViewById(R.id.activity_note_editor__title_edit)
        text = findViewById(R.id.activity_note_editor__text_edit)

        val backButton = findViewById<Button>(R.id.activity_note_editor__back_btn)
        val saveButton = findViewById<Button>(R.id.activity_note_editor__save_btn)
        backButton.setOnClickListener { finish() }
        saveButton.setOnClickListener { saveNote() }

        id = intent.extras?.getLong(NOTE_ID_START_EDITOR)
        if (savedInstanceState == null) {
            id?.let { presenter.loadNoteById(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onNoteByIdLoaded(note: Note) {
        id = note.id
        title.setText(note.title)
        text.setText(note.text)
    }

    override fun onSavingFinished() {
        finish()
    }

    private fun saveNote() {
        if (validateTitleAndText()) {
            val note = Note(id, title.text.toString(), text.text.toString())

            if (note.id == null) {
                presenter.insertNote(note)
            } else {
                presenter.updateNote(note)
            }
        }
    }

    private fun validateTitleAndText(): Boolean {
        if (title.text.isBlank()) {
            showToast(getString(R.string.editor_title_is_blank_msg))
            return false
        }
        if (text.text.isBlank()) {
            showToast(getString(R.string.editor_title_is_blank_msg))
            return false
        }
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}