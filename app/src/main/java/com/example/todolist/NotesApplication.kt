package com.example.todolist

import android.app.Application
import androidx.room.Room
import com.example.todolist.persistence.AppDatabase

class NotesApplication: Application() {

    companion object {
        lateinit var instance: NotesApplication
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }


}