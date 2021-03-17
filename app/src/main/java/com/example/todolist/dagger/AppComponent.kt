package com.example.todolist.dagger

import com.example.todolist.NotesApplication
import com.example.todolist.activities.NoteListActivity
import com.example.todolist.mvp.model.NoteListModel
import com.example.todolist.mvp.presenter.NoteListPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class, DatabaseModule::class, ExecutorServiceModule::class, NoteListMvpModule::class])
@Singleton
interface AppComponent {

    fun inject(noteListActivity: NoteListActivity)

    fun inject(noteListPresenter: NoteListPresenter)

    fun inject(noteListModel: NoteListModel)

    fun inject(notesApplication: NotesApplication)

}