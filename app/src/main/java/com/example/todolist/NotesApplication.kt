package com.example.todolist

import android.app.Application
import com.example.todolist.dagger.*

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
                .executorServiceModule(ExecutorServiceModule())
                .noteListMvpModule(NoteListMvpModule())
                .databaseModule(DatabaseModule())
                .build()
    }

}