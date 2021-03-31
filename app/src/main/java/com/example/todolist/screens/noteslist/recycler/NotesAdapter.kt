package com.example.todolist.screens.noteslist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.persistence.Note

class NotesAdapter(
    private val onNoteClicked: (Note) -> Unit
): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var items = emptyList<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.title.setOnClickListener { onNoteClicked(items[position]) }
    }

    override fun getItemCount(): Int = items.size

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.note_list_item__title)

    }

}