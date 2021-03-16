package com.example.todolist.dagger

import android.content.Context
import androidx.room.Room
import com.example.todolist.persistence.AppDatabase
import com.example.todolist.persistence.NoteDao
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @NotNull
    @Singleton
    fun provideAppDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    @Provides
    @NotNull
    @Singleton
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

}