package com.example.todolist

import android.app.Application
import com.example.todolist.dagger.AppComponent
import com.example.todolist.dagger.AppContextModule
import com.example.todolist.dagger.DaggerAppComponent
import com.example.todolist.dagger.DatabaseModule

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