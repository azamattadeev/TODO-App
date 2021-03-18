package com.example.todolist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.persistence.Note

class NotesAdapter(items: List<Note>,
                   private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var items = items
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
        holder.title.setOnClickListener { onItemClickListener.onItemClicked(position) }
    }

    override fun getItemCount(): Int = items.size

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.note_list_item__title)

    }

}