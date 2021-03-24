package com.example.todolist.dagger

import com.example.todolist.screens.noteeditor.NoteEditorModel
import com.example.todolist.screens.noteeditor.NoteEditorModelImpl
import com.example.todolist.screens.noteeditor.NoteEditorPresenter
import com.example.todolist.persistence.NoteDao
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
class NoteEditorMvpModule {

    @Provides
    @NotNull
    @Singleton
    fun provideNoteEditorPresenter(noteEditorModel: NoteEditorModel): NoteEditorPresenter {
        return NoteEditorPresenter(noteEditorModel)
    }

    @Provides
    @NotNull
    @Singleton
    fun provideNoteEditorModel(noteDao: NoteDao, executorService: ExecutorService): NoteEditorModel {
        return NoteEditorModelImpl(noteDao, executorService)
    }

}