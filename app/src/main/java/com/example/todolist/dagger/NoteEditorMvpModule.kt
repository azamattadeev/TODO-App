package com.example.todolist.dagger

import com.example.todolist.screens.noteeditor.NoteEditorModel
import com.example.todolist.screens.noteeditor.NoteEditorModelImpl
import com.example.todolist.screens.noteeditor.NoteEditorPresenter
import com.example.todolist.screens.noteeditor.NoteEditorPresenterImpl
import dagger.Binds
import dagger.Module
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
interface NoteEditorMvpModule {

    @Binds
    @NotNull
    @Singleton
    fun provideNoteEditorPresenter(noteEditorPresenter: NoteEditorPresenterImpl): NoteEditorPresenter

    @Binds
    @NotNull
    @Singleton
    fun provideNoteEditorModel(noteEditorModelImpl: NoteEditorModelImpl): NoteEditorModel

}