package com.example.todolist.dagger

import com.example.todolist.mvp.model.NoteListModel
import com.example.todolist.mvp.model.NoteListModelImpl
import com.example.todolist.mvp.presenter.NoteListPresenter
import com.example.todolist.persistence.NoteDao
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @NotNull
    @Singleton
    fun provideNoteListPresenter(noteListModel: NoteListModel): NoteListPresenter {
        return NoteListPresenter(noteListModel)
    }

    @Provides
    @NotNull
    @Singleton
    fun provideNoteListModel(noteDao: NoteDao, executorService: ExecutorService): NoteListModel {
        return NoteListModelImpl(noteDao, executorService)
    }

}