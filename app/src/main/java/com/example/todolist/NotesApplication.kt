package com.example.todolist

import android.app.Application
import com.example.todolist.dagger.AppComponent
import com.example.todolist.dagger.DaggerAppComponent
import com.example.todolist.dagger.DatabaseModule
import com.example.todolist.dagger.AppContextModule

class NotesApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerAppComponent.builder()
             .appContextModule(AppContextModule(this))
             .databaseModule(DatabaseModule())
             .build()
    }

}