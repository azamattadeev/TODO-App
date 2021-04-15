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

    var items = mutableListOf<Note>()
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

    fun insertNote(note: Note) {
        items.add(0, note)
        notifyItemInserted(0)
    }

    fun updateNote(note: Note) {
        val position = items.indexOf(items.find { it.id == note.id })
        items[position] = note
        notifyItemChanged(position)
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.note_list_item__title)

    }

}