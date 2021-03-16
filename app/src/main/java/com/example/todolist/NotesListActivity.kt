package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.persistence.NoteDao
import javax.inject.Inject

class NotesListActivity: AppCompatActivity() {

    @Inject lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotesApplication.appComponent.inject(this)
    }

}