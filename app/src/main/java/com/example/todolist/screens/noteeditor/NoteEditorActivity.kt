package com.example.todolist.screens.noteeditor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.NOTE_ID_START_EDITOR
import com.example.todolist.NotesApplication
import com.example.todolist.R
import javax.inject.Inject

class NoteEditorActivity: AppCompatActivity(), NoteEditorViewModel.VMStateObserver {

    @Inject
    lateinit var viewModel: NoteEditorViewModel

    private lateinit var title: EditText
    private lateinit var text: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        NotesApplication.appComponent.inject(this)

        viewModel.observableState.subscribe(this)

        title = findViewById(R.id.activity_note_editor__title_edit)
        text = findViewById(R.id.activity_note_editor__text_edit)

        val backButton = findViewById<Button>(R.id.activity_note_editor__back_btn)
        val saveButton = findViewById<Button>(R.id.activity_note_editor__save_btn)
        backButton.setOnClickListener { onNeedFinish()}
        saveButton.setOnClickListener { saveNote() }

        if (savedInstanceState == null) {
            viewModel.observableState.id = intent.extras?.getLong(NOTE_ID_START_EDITOR)
            viewModel.loadNote()
        } else {
            checkViewModelState()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.observableState.unsubscribe(this)
    }

    override fun onTitleLoaded(title: String) {
        this.title.setText(title)
    }

    override fun onTextLoaded(text: String) {
        this.text.setText(text)
    }

    override fun onNeedFinish() {
        viewModel.clear()
        finish()
    }

    override fun onShowToast(message: String) {
        showToast(message)
        viewModel.observableState.toastText = null
    }

    private fun checkViewModelState() {
        if (viewModel.observableState.needFinish) onNeedFinish()
        val toastText = viewModel.observableState.toastText
        if (toastText != null) onShowToast(toastText)
    }

    private fun saveNote() {
        viewModel.observableState.title = title.text.toString()
        viewModel.observableState.text = text.text.toString()
        viewModel.saveNote()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}