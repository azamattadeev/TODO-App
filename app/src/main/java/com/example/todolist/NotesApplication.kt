package com.example.todolist

import android.app.Application
import com.example.todolist.dagger.AppComponent
import com.example.todolist.dagger.DaggerAppComponent
import com.example.todolist.dagger.DatabaseModule
import com.example.todolist.dagger.AppContextModule
import com.example.todolist.persistence.Note
import com.example.todolist.persistence.NoteDao
import java.util.concurrent.Executors
import javax.inject.Inject

class NotesApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(AppContextModule(this))
                .databaseModule(DatabaseModule())
                .build()
    }

}