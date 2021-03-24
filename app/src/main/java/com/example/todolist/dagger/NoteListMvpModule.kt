package com.example.todolist.dagger

import com.example.todolist.screens.noteslist.NotesListModel
import com.example.todolist.screens.noteslist.NotesListModelImpl
import com.example.todolist.screens.noteslist.NotesListPresenter
import com.example.todolist.persistence.NoteDao
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
class NoteListMvpModule {

    @Provides
    @NotNull
    @Singleton
    fun provideNoteListPresenter(notesListModel: NotesListModel): NotesListPresenter {
        return NotesListPresenter(notesListModel)
    }

    @Provides
    @NotNull
    @Singleton
    fun provideNoteListModel(noteDao: NoteDao, executorService: ExecutorService): NotesListModel {
        return NotesListModelImpl(noteDao, executorService)
    }

}