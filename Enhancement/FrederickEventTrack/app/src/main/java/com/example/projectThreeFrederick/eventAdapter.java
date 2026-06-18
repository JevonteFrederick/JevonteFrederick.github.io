package com.example.projectThreeFrederick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//custom event adapter
public class eventAdapter extends RecyclerView.Adapter<eventAdapter.EventViewHolder>{
    private final List<event> events;
    private final OnEventClickListener listener;

    //interface for delete and click buttons for each event
    public interface OnEventClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public eventAdapter(List<event> events, OnEventClickListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        event event = events.get(position);

        // set text for title, date, and notes
        holder.textViewTitle.setText(event.getName());
        holder.textViewDate.setText(event.getDate());
        holder.textViewNote.setText(event.getNote());

        //set button for delete
        holder.buttonDelete.setOnClickListener(v -> {
            if (listener != null) {
                int adapterPosition = holder.getBindingAdapterPosition();
                listener.onDeleteClick(adapterPosition);
            }
        });

        //set button for Edit
        holder.buttonEdit.setOnClickListener(v -> {
            if (listener != null) {
                int adapterPosition = holder.getBindingAdapterPosition();
                listener.onEditClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return events;
        return events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewTitle;  //event title text
        final TextView textViewDate;   //event date text
        final TextView textViewNote;   //event notes text
        final ImageButton buttonDelete; //delete button
        final ImageButton buttonEdit; //edit button
        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewEventName);
            textViewDate = itemView.findViewById(R.id.textViewEventDate);
            textViewNote = itemView.findViewById(R.id.textViewEventNotes);
            buttonDelete = itemView.findViewById(R.id.imageButtonDelete);
            buttonEdit = itemView.findViewById(R.id.imageButtonEdit);
        }
    }
}
