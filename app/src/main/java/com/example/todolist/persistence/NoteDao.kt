package com.example.todolist.persistence

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): Note

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}