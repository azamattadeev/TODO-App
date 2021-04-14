package com.example.todolist.dagger

import com.example.todolist.screens.noteeditor.NoteEditorActivity
import com.example.todolist.screens.noteslist.NotesListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class, DatabaseModule::class, ExecutorServiceModule::class])
@Singleton
interface AppComponent {

    fun inject(notesListActivity: NotesListActivity)

    fun inject(noteEditorActivity: NoteEditorActivity)

}