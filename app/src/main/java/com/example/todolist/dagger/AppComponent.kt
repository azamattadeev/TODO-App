package com.example.todolist.dagger

import com.example.todolist.NotesApplication
import com.example.todolist.screens.noteeditor.NoteEditorActivity
import com.example.todolist.screens.noteslist.NotesListActivity
import com.example.todolist.screens.noteslist.NotesListModel
import com.example.todolist.screens.noteslist.NotesListPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class, DatabaseModule::class, ExecutorServiceModule::class, NoteListMvpModule::class, NoteEditorMvpModule::class])
@Singleton
interface AppComponent {

    fun inject(notesListActivity: NotesListActivity)

    fun inject(noteEditorActivity: NoteEditorActivity)

    fun inject(notesListPresenter: NotesListPresenter)

    fun inject(notesListModel: NotesListModel)

    fun inject(notesApplication: NotesApplication)

}