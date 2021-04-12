package com.example.todolist.dagger

import com.example.todolist.screens.noteslist.NotesListModel
import com.example.todolist.screens.noteslist.NotesListModelImpl
import com.example.todolist.screens.noteslist.NotesListPresenter
import com.example.todolist.screens.noteslist.NotesListPresenterImpl
import dagger.Binds
import dagger.Module
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
interface NoteListMvpModule {

    @Binds
    @NotNull
    @Singleton
    fun provideNotesListPresenter(notesListPresenter: NotesListPresenterImpl): NotesListPresenter

    @Binds
    @NotNull
    @Singleton
    fun provideNotesListModel(notesListModelImpl: NotesListModelImpl): NotesListModel

}