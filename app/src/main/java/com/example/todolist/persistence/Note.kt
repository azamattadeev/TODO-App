package com.example.todolist.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String,
    val text: String
)