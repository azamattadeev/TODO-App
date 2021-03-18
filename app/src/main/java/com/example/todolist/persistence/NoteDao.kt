package com.example.todolist.persistence

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllOrderByIdDesc(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Long): Note

    @Insert
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}