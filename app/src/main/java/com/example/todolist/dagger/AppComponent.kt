package com.example.todolist.dagger

import com.example.todolist.NotesListActivity
import com.example.todolist.mvp.model.NotesModelImpl
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {

    fun inject(notesModelImpl: NotesModelImpl)

    fun inject(notesListActivity: NotesListActivity)

}